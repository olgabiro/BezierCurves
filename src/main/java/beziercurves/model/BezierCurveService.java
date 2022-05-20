package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

public class BezierCurveService {

    private final BezierCurveIncreaseDegreeProcessor increaseDegreeProcessor;
    private final BezierCurveDecreaseDegreeProcessor decreaseDegreeProcessor;
    private final BezierCurveApproximateProcessor approximateProcessor;
    private final BezierCurveHermiteProcessor hermiteProcessor;

    public BezierCurveService(final BezierCurveIncreaseDegreeProcessor increaseDegreeProcessor,
                              final BezierCurveDecreaseDegreeProcessor decreaseDegreeProcessor,
                              final BezierCurveApproximateProcessor approximateProcessor,
                              final BezierCurveHermiteProcessor hermiteProcessor) {

        this.increaseDegreeProcessor = assertNotNull(increaseDegreeProcessor);
        this.decreaseDegreeProcessor = assertNotNull(decreaseDegreeProcessor);
        this.approximateProcessor = assertNotNull(approximateProcessor);
        this.hermiteProcessor = assertNotNull(hermiteProcessor);
    }

    public BezierCurve increaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        return this.increaseDegreeProcessor.increaseDegree(curve);
    }

    public BezierCurve decreaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        if (curve.canDegreeBeLowered()) {
            return this.decreaseDegreeProcessor.decreaseDegree(curve);
        }
        return curve;
    }

    public BezierCurve approximate(final BezierCurve curve) {
        assertNotNull(curve);
        if (curve.canDegreeBeLowered()) {
            return this.approximateProcessor.approximate(curve);
        }
        return curve;
    }
    public BezierCurve decreaseDegreeHermite(final BezierCurve curve) {
        assertNotNull(curve);
        return this.hermiteProcessor.decreaseDegree(curve);
    }
}