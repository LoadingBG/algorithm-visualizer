package visualizer.algorithms;

public interface StepAlgorithm {
    boolean hasNextStep();
    StepInfo nextStep();
}
