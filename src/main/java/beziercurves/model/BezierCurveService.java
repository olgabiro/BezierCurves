package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

public class BezierCurveService {

    private final BezierCurveIncreaseDegreeProcessor increaseDegreeProcessor;
    private final BezierCurveDecreaseDegreeProcessor decreaseDegreeProcessor;
    private final BezierCurveApproximateProcessor approximateProcessor;

    public BezierCurveService(final BezierCurveIncreaseDegreeProcessor increaseDegreeProcessor,
                              final BezierCurveDecreaseDegreeProcessor decreaseDegreeProcessor,
                              final BezierCurveApproximateProcessor approximateProcessor) {

        this.increaseDegreeProcessor = assertNotNull(increaseDegreeProcessor);
        this.decreaseDegreeProcessor = assertNotNull(decreaseDegreeProcessor);
        this.approximateProcessor = assertNotNull(approximateProcessor);
    }

    public BezierCurve increaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        return this.increaseDegreeProcessor.increaseDegree(curve);
    }

    public BezierCurve decreaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        return this.decreaseDegreeProcessor.decreaseDegree(curve);
    }

    public BezierCurve approximate(final BezierCurve curve) {
        assertNotNull(curve);
        return this.approximateProcessor.approximate(curve);
    }
}