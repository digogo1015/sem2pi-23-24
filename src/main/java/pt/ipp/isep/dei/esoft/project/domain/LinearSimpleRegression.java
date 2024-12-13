package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math.distribution.FDistribution;
import org.apache.commons.math.distribution.FDistributionImpl;
import org.apache.commons.math.distribution.TDistribution;
import org.apache.commons.math.distribution.TDistributionImpl;
import org.apache.commons.math.stat.regression.SimpleRegression;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The `LinearSimpleRegression` class performs simple linear regression analysis.
 * It provides methods for displaying the regression model, calculating predictions,
 * retrieving information about the model, calculating confidence intervals, performing
 * hypothesis testing, and conducting ANOVA analysis.
 */
public class LinearSimpleRegression {

    // Instance variables
    private TDistribution distribution;
    private SimpleRegression simpleRegression;
    private FDistribution fadistribution;
    private long n;
    private double a;
    private double b;
    private DecimalFormat df = new DecimalFormat("#.####");

    /**
     * Constructs a `LinearSimpleRegression` object with the given `SimpleRegression` model.
     *
     * @param simpleRegression The `SimpleRegression` model used for linear regression analysis.
     */
    public LinearSimpleRegression(SimpleRegression simpleRegression) {
        // Initialize instance variables
        this.simpleRegression = simpleRegression;
        this.n = simpleRegression.getN();
        this.distribution = new TDistributionImpl(n - 2);
        this.fadistribution = new FDistributionImpl(1, (n - 2));
        this.a = simpleRegression.getIntercept();
        this.b = simpleRegression.getSlope();
    }

    /**
     * Returns the string representation of the simple regression model.
     *
     * @return A string representing the simple regression model.
     */
    public String displaySimpleRegression() {
        // Return the string representation of the regression model
        return ("a = " + df.format(a) + "\nb = " + df.format(b) + "\nRegression line : y = " + df.format(a) + " + " + df.format(b) + "x");
    }

    /**
     * Calculates the prediction for a given x value based on the simple regression model.
     *
     * @param x The x value for which the prediction is calculated.
     * @return A string representing the prediction for the given x value.
     */
    public String simpleRegressionPrediction(double x) {
        // Calculate the prediction for the given x value
        return ("Prediction for x = " + x + " is " + df.format(simpleRegression.predict(x)));
    }

    /**
     * Retrieves information about the simple regression model.
     *
     * @return A string containing information about the simple regression model.
     */
    public String simpleRegressionInfo() {
        // Retrieve information about the simple regression model
        double s = Math.sqrt(simpleRegression.getMeanSquareError());
        double Sxx = simpleRegression.getXSumSquares();
        double Syy = simpleRegression.getTotalSumSquares();
        double Sxy = simpleRegression.getSumOfCrossProducts();
        double R = simpleRegression.getR();
        double R2 = simpleRegression.getRSquare();

        return ("n = " + n + "\ns = " + df.format(s) + "\nSxx = " + df.format(Sxx) + "\nSyy = " + df.format(Syy) + "\nSxy = " + df.format(Sxy) + "\nR = " + df.format(R) + "\nR2 = " + df.format(R2));
    }

    /**
     * Returns the critical t-value for a given alpha (significance level).
     *
     * @param alpha The significance level (e.g., 0.05 for a 95% confidence level).
     * @return The critical t-value.
     */
    public double getTC(double alpha) {
        double tc = 0;
        try {
            tc = distribution.inverseCumulativeProbability(1.0 - alpha / 2.0);
        } catch (Exception ignored) {
        }
        return tc;
    }

    /**
     * Calculates the confidence intervals for the intercept and slope of the simple regression model.
     *
     * @param IC The confidence level in percentage (e.g., 95 for a 95% confidence level).
     * @return A string containing the confidence intervals for the intercept and slope.
     */
    public String simpleRegressionConfidence(double IC) {
        double alpha = (100 - IC) / 100;
        double tc = getTC(alpha);
        double aConfidence = simpleRegression.getInterceptStdErr() * tc;
        double bConfidence = 0;

        try {
            bConfidence = simpleRegression.getSlopeConfidenceInterval(alpha);
        } catch (Exception ignored) {
        }

        return ("Confidence Interval for a: [" + df.format((a - aConfidence)) + " , " + df.format((a + aConfidence)) + "]" +
                "\nConfidence Interval for b: [" + df.format((b - bConfidence)) + " , " + df.format((b + bConfidence)) + "]");
    }

    /**
     * Performs hypothesis testing for the intercept and slope of the simple regression model.
     *
     * @param a0 The hypothesized value for the intercept.
     * @param b0 The hypothesized value for the slope.
     * @param IC The confidence level in percentage (e.g., 95 for a 95% confidence level).
     * @return A string containing the results of the hypothesis testing.
     */
    public String simpleRegressionHypothesisTest(double a0, double b0, double IC) {
        double alpha = (100 - IC) / 100;
        double tc = getTC(alpha);

        double testeDeHipoteseA = Math.abs((simpleRegression.getIntercept() - a0) / simpleRegression.getInterceptStdErr());
        double testeDeHipoteseB = Math.abs((simpleRegression.getSlope() - b0) / simpleRegression.getSlopeStdErr());

        String mensagem = "H0 : a = " + a0 + " v.s H1 : a ≠  " + a0;

        if (testeDeHipoteseA > tc)
            mensagem = mensagem + "\n As |ta| = " + df.format(testeDeHipoteseA) + " > " + df.format(tc) + ", we reject H0";
        else
            mensagem = mensagem + "\n As |ta| = " + df.format(testeDeHipoteseA) + " < " + df.format(tc) + ", we reject H1";

        mensagem = mensagem + "\nH0 : b = " + b0 + " v.s H1 : b ≠  " + b0;

        if (testeDeHipoteseB > tc)
            mensagem = mensagem + "\n As |tb| = " + df.format(testeDeHipoteseB) + " > " + df.format(tc) + ", we reject H0";
        else
            mensagem = mensagem + "\n As |tb| = " + df.format(testeDeHipoteseB) + " < " + df.format(tc) + ", we reject H1";
        return mensagem;
    }

    /**
     * Performs the ANOVA (Analysis of Variance) for the simple regression model.
     *
     * @return An ArrayList of `AnovaTable` objects containing the ANOVA table values.
     */
    public ArrayList<AnovaTable> SimpleRegressionANOVA(double IC) {
        double alpha = (100 - IC) / 100;
        double SR = simpleRegression.getRegressionSumSquares();
        double SE = simpleRegression.getSumSquaredErrors();
        double ST = simpleRegression.getTotalSumSquares();
        double MSE = simpleRegression.getSumSquaredErrors() / (n - 2);
        double F = SR / MSE;
        double FSignificancia = 0;

        try {
            FSignificancia = fadistribution.inverseCumulativeProbability(1-alpha);
        } catch (Exception ignored) {
        }

        String mensagem = "";

        if (F > FSignificancia)
            mensagem = mensagem + "\nAs F = " + df.format(F) + " > " + df.format(FSignificancia) + ", we consider the linear regression model is linear";
        else
            mensagem = mensagem + "\nAs F = " + df.format(F) + " < " + df.format(FSignificancia) + ", we consider the linear regression model is not linear";

        ArrayList<AnovaTable> anovaValues = new ArrayList<>();

        anovaValues.add(new AnovaTable("Regression", df.format(SR), "1", df.format(SR), df.format(F), df.format(FSignificancia)));
        anovaValues.add(new AnovaTable("Error", df.format(SE), "" + (n - 2), df.format(MSE), "", ""));
        anovaValues.add(new AnovaTable("Total", df.format(ST), "" + (n - 1), "", "", ""));
        anovaValues.add(new AnovaTable(mensagem, "", "", "", "", ""));

        return anovaValues;
    }
}
