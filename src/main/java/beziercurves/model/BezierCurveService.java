package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

public class BezierCurveService {

    private final BezierCurveIncreaseDegreeProcessor bezierCurveIncreaseDegreeProcessor;
    private final BezierCurveDecreaseDegreeProcessor bezierCurveDecreaseDegreeProcessor;

    public BezierCurveService(final BezierCurveIncreaseDegreeProcessor increaseDegreeProcessor,
                              final BezierCurveDecreaseDegreeProcessor decreaseDegreeProcessor) {

        this.bezierCurveIncreaseDegreeProcessor = assertNotNull(increaseDegreeProcessor);
        this.bezierCurveDecreaseDegreeProcessor = assertNotNull(decreaseDegreeProcessor);
    }

    public BezierCurve increaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        return this.bezierCurveIncreaseDegreeProcessor.increaseDegree(curve);
    }

    public BezierCurve decreaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        return this.bezierCurveDecreaseDegreeProcessor.decreaseDegree(curve);
    }
}