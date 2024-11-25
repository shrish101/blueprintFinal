package use_case.edit_message;

/**
 * The EditInputBoundry interface defines the contract for the input boundary
 * of the Edit Password Use Case. It provides a method for executing the
 * use case with the given input data.
 */
public interface EditInputBoundry {
    /**
     * Execute the Edit Password Use Case.
     * @param editInputData the input data for this use case
     */
    void execute(EditInputData editInputData);
}
