package Command;


import java.util.Stack;

/**
 * Implementing the Command design pattern to make every CRUD operation
 * (Create, Update, Delete) a Command with its own execute() and undo() method
 */
public class CommandManager {
    private Stack<Command> history = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command) {
        command.execute(); // Execute command
        history.push(command); // Add command to history
        redoStack.clear(); // Reset redo stack
    }

    public void undoCommand() {
        if (!history.isEmpty()){
            Command command = history.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redoCommand() {
        if (!redoStack.isEmpty()){
            Command command = redoStack.pop();
            command.execute();
            history.push(command);
        }
    }
}
