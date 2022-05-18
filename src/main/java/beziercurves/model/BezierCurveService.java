package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

public class BezierCurveService {

    private final BezierCurveIncreaseDegreeProcessor increaseDegreeProcessor;
    private final BezierCurveDecreaseDegreeProcessor decreaseDegreeProcessor;
    private final BezierCurveHermiteProcessor hermiteProcessor;

    public BezierCurveService(final BezierCurveIncreaseDegreeProcessor increaseDegreeProcessor,
                              final BezierCurveDecreaseDegreeProcessor decreaseDegreeProcessor,
                              final BezierCurveHermiteProcessor hermiteProcessor) {

        this.increaseDegreeProcessor = assertNotNull(increaseDegreeProcessor);
        this.decreaseDegreeProcessor = assertNotNull(decreaseDegreeProcessor);
        this.hermiteProcessor = assertNotNull(hermiteProcessor);
    }

    public BezierCurve increaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        return this.increaseDegreeProcessor.increaseDegree(curve);
    }

    public BezierCurve decreaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        return this.decreaseDegreeProcessor.decreaseDegree(curve);
    }

    public BezierCurve decreaseDegreeHermite(final BezierCurve curve) {
        assertNotNull(curve);
        return this.hermiteProcessor.decreaseDegree(curve);
    }
}