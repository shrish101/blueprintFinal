package use_case.messaging;

/**
 * Input Boundary for actions related messaging.
 */
public interface MessageInputBoundary {
    /**
     * Executes the login use case.
     * @param messageInputData the input data
     */
    void execute(MessageInputData messageInputData);
}
