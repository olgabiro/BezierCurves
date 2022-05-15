package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

public class BezierCurveService {

    private final BezierCurveIncreaseDegreeProcessor bezierCurveIncreaseDegreeProcessor;

    public BezierCurveService(final BezierCurveIncreaseDegreeProcessor bezierCurveIncreaseDegreeProcessor) {
        this.bezierCurveIncreaseDegreeProcessor = bezierCurveIncreaseDegreeProcessor;
    }

    public BezierCurve increaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        return this.bezierCurveIncreaseDegreeProcessor.increaseDegree(curve);
    }
}