Batch Example
 ON THIS PAGE
WHY BATCH?
USAGE CONSTRAINTS
HOW TO USE BATCH
SPLIT INTO LISTS
Why batch?
Sending messages in batch improves performance of delivering small messages.

Usage constraints
Messages of the same batch should have: same topic, same waitStoreMsgOK and no schedule support.

Besides, the total size of the messages in one batch should be no more than 1MiB.

How to use batch
If you just send messages of no more than 1MiB at a time, it is easy to use batch:
```java
String topic = "BatchTest";
List<Message> messages = new ArrayList<>();
messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
try {
    producer.send(messages);
} catch (Exception e) {
    e.printStackTrace();
    //handle the error
}
```
    
Split into lists
The complexity only grow when you send large batch and you may not sure if it exceeds the size limit (1MiB).

At this time, you’d better split the lists:


```java
public class ListSplitter implements Iterator<List<Message>> {
    private final int SIZE_LIMIT = 1000 * 1000;
    private final List<Message> messages;
    private int currIndex;
    public ListSplitter(List<Message> messages) {
            this.messages = messages;
    }
    @Override public boolean hasNext() {
        return currIndex < messages.size();
    }
    @Override public List<Message> next() {
        int nextIndex = currIndex;
        int totalSize = 0;
        for (; nextIndex < messages.size(); nextIndex++) {
            Message message = messages.get(nextIndex);
            int tmpSize = message.getTopic().length() + message.getBody().length;
            Map<String, String> properties = message.getProperties();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                tmpSize += entry.getKey().length() + entry.getValue().length();
            }
            tmpSize = tmpSize + 20; //for log overhead
            if (tmpSize > SIZE_LIMIT) {
                //it is unexpected that single message exceeds the SIZE_LIMIT
                //here just let it go, otherwise it will block the splitting process
                if (nextIndex - currIndex == 0) {
                   //if the next sublist has no element, add this one and then break, otherwise just break
                   nextIndex++;  
                }
                break;
            }
            if (tmpSize + totalSize > SIZE_LIMIT) {
                break;
            } else {
                totalSize += tmpSize;
            }
    
        }
        List<Message> subList = messages.subList(currIndex, nextIndex);
        currIndex = nextIndex;
        return subList;
    }
}
//then you could split the large list into small ones:
ListSplitter splitter = new ListSplitter(messages);
while (splitter.hasNext()) {
   try {
       List<Message>  listItem = splitter.next();
       producer.send(listItem);
   } catch (Exception e) {
       e.printStackTrace();
       //handle the error
   }
}

```