import java.util.Stack;

class QueueUsingStacks {

    private Stack<Integer> enqueueStack;
    private Stack<Integer> dequeueStack;

    public QueueUsingStacks() {
        this.enqueueStack = new Stack<>();
        this.dequeueStack = new Stack<>();
    }

    // Enqueue operation: Push the element onto the enqueueStack
    public void enqueue(int value) {
        enqueueStack.push(value);
    }

    // Dequeue operation:
    // 1. If dequeueStack is empty, transfer all elements from enqueueStack to dequeueStack
    // 2. Pop from dequeueStack
    public int dequeue() {
        if (dequeueStack.isEmpty()) {
            if (enqueueStack.isEmpty()) {
                throw new IllegalStateException("Queue is empty"); // Check for underflow
            }
            // Transfer elements from enqueueStack to dequeueStack
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
        return dequeueStack.pop();
    }

    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }

    public int peek()
    {
        if (dequeueStack.isEmpty()) {
            if (enqueueStack.isEmpty()) {
                throw new IllegalStateException("Queue is empty"); // Check for underflow
            }
            // Transfer elements from enqueueStack to dequeueStack
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
        return dequeueStack.peek();
    }
}

public class QueueStack {
    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("Peek: " + queue.peek()); // 1
        System.out.println("Dequeue: " + queue.dequeue()); // 1
        System.out.println("Dequeue: " + queue.dequeue()); // 2

        queue.enqueue(4);
        System.out.println("Dequeue: " + queue.dequeue()); //3
        System.out.println("isEmpty: " + queue.isEmpty()); // false
        System.out.println("Dequeue: " + queue.dequeue()); // 4
        System.out.println("isEmpty: " + queue.isEmpty()); // true

    }
}