package pt.ipp.isep.dei.esoft.project.domain;

import kotlin.Pair;
import org.apache.commons.math.distribution.*;
import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Performs multiple linear regression analysis.
 */
public class LinearMultipleRegression {

    private OLSMultipleLinearRegression multipleLinearRegression;
    private double[] y;
    private double[][] x;
    private TDistribution distribution;
    private FDistribution fadistribution;
    private double n;
    private double k;
    private double betas[];
    private double XtX[][];
    private DecimalFormat df = new DecimalFormat("#.####");

    /**
     * Creates a LinearMultipleRegression object based on the provided data.
     *
     * @param XY A pair of arrays representing the dependent variable values (y) and the independent variable values (x).
     */
    public LinearMultipleRegression(Pair<double[], double[][]> XY) {
        this.y = XY.getFirst();
        this.x = XY.getSecond();
        this.multipleLinearRegression = getMultipleLinearRegression(y, x);
        this.n = x.length;
        this.k = x[0].length;
        this.distribution = new TDistributionImpl((n - (k + 1)));
        this.fadistribution = new FDistributionImpl((k), (n - (k + 1)));
        this.betas = multipleLinearRegression.estimateRegressionParameters();
        this.XtX = multipleLinearRegression.estimateRegressionParametersVariance();
    }

    /**
     * Performs multiple linear regression analysis using the provided dependent variable and independent variables.
     *
     * @param y The dependent variable values.
     * @param x The independent variable values.
     * @return The OLSMultipleLinearRegression object representing the multiple linear regression analysis.
     */
    public OLSMultipleLinearRegression getMultipleLinearRegression(double[] y, double[][] x) {
        OLSMultipleLinearRegression multipleRegression = new OLSMultipleLinearRegression();
        multipleRegression.newSampleData(y, x);
        return multipleRegression;
    }

    /**
     * Generates a string representation of the multiple regression model.
     *
     * @return The string representation of the multiple regression model.
     */
    public String displayMultipleRegression() {
        String model = "Regression Model:\n Ŷ = " + betas[0];
        for (int i = 1; i < betas.length; i++) {
            model += (" + " + df.format(betas[i]) + "X" + i);
        }
        return model;
    }

    /**
     * Performs a prediction using the multiple regression model and the provided input variables.
     *
     * @param x1 The value of the first input variable.
     * @param x2 The value of the second input variable.
     * @param x3 The value of the third input variable.
     * @param x4 The value of the fourth input variable.
     * @param x5 The value of the fifth input variable.
     * @return The prediction for the given input variables.
     */
    public String multipleRegressionPrediction(double x1, double x2, double x3, double x4, double x5) {
        double prediction = betas[0] + (betas[1] * x1) + (betas[2] * x2) + (betas[3] * x3) + (betas[4] * x4) + (betas[5] * x5);
        return ("For: X1 = " + df.format(x1) + ", X2 = " + df.format(x2) + ", X3 = " + df.format(x3) + ",  X4 = " + df.format(x4) + ", X5 = " + df.format(x5) + " is " + df.format(prediction));
    }

    /**
     * Retrieves information about the multiple regression analysis.
     *
     * @return A string containing information about the multiple regression analysis, including the sample size (n),
     * number of predictors (k), sum of squared errors (SQE), total sum of squares (SQT),
     * sum of squares due to regression (SQR), coefficient of determination (R2),
     * adjusted coefficient of determination (R2Adjusted), and mean squared error (σ^2).
     */
    public String multipleRegressionInfo() {
        double SQE = multipleLinearRegression.calculateResidualSumOfSquares();
        double SQT = multipleLinearRegression.calculateTotalSumOfSquares();
        double SQR = SQT - SQE;
        double MQE = SQE / (n - (k + 1));

        return ("n = " + n + "\nk = " + k + "\nSQE = "
                + df.format(SQE) + "\nSQT = " + df.format(SQT) + "\nSQR = " + df.format(SQR) + "\nR2 = " + df.format(multipleLinearRegression.calculateRSquared())
                + "\nR2Adjusted = " + df.format(multipleLinearRegression.calculateAdjustedRSquared()) + "\nσ^2 =" + df.format(MQE));
    }

    /**
     * Performs a hypothesis test for a specific beta coefficient in multiple regression.
     *
     * @param IC             The confidence level of the hypothesis test.
     * @param betaPretendido The index of the beta coefficient being tested.
     * @return A string describing the hypothesis test result, including the null and alternative hypotheses,
     * the test statistic (t0), and the decision whether to reject the null hypothesis or not.
     */
    public String multipleRegressionHypothesisTest(double IC, int betaPretendido) {
        double alpha = (100 - IC) / 100;
        double tc = 0;

        try {
            tc = distribution.inverseCumulativeProbability(1.0 - alpha / 2.0);
        } catch (Exception ignored) {
        }

        double X0 = Math.abs((betas[betaPretendido]) / (Math.sqrt((multipleLinearRegression.estimateErrorVariance()) * XtX[betaPretendido][betaPretendido])));

        String mensagem = "H0 : β" + df.format(betaPretendido) + " = 0  v.s  H1 : β" + df.format(betaPretendido) + "≠  0";

        if (X0 > tc)
            mensagem = mensagem + "\nAs |t0| = " + df.format(X0) + " > " + df.format(tc) + ", we reject H0";
        else
            mensagem = mensagem + "\nAs |t0| = " + df.format(X0) + " < " + df.format(tc) + ", we reject H1";

        return mensagem;
    }


    /**
     * Calculates the confidence interval for a specific beta coefficient in multiple regression.
     *
     * @param IC             The confidence level of the interval.
     * @param betaPretendido The index of the beta coefficient.
     * @return A string representing the confidence interval for the specified beta coefficient.
     */
    public String multipleRegressionBetaConfidence(double IC, int betaPretendido) {
        double alpha = (100 - IC) / 100;
        double tc = 0;

        try {
            tc = distribution.inverseCumulativeProbability(1.0 - alpha / 2.0);
        } catch (Exception ignored) {
        }

        double SQE = multipleLinearRegression.calculateResidualSumOfSquares();
        double s2 = SQE / (n - (k + 1));

        double betaConfidence = tc * Math.sqrt((s2 * XtX[betaPretendido][betaPretendido]));

        return "Confidence Interval for β" + betaPretendido + ": [" + df.format((betas[betaPretendido] - betaConfidence)) +
                " , " + df.format((betas[betaPretendido] + betaConfidence)) + "]";
    }

    /**
     * Performs the ANOVA analysis for multiple regression.
     *
     * @param IC The confidence level for hypothesis testing.
     * @return An ArrayList of AnovaTable objects containing the ANOVA table values.
     */
    public ArrayList<AnovaTable> multipleRegressionAnova(double IC) {
        double alpha = (100 - IC) / 100;
        double SQE = multipleLinearRegression.calculateResidualSumOfSquares();
        double SQT = multipleLinearRegression.calculateTotalSumOfSquares();
        double SQR = SQT - SQE;
        double MQR = SQR / k;
        double MQE = SQE / (n - (k + 1));
        double F = MQR / MQE;
        double fa = 0;

        try {
            fa = fadistribution.inverseCumulativeProbability(1-alpha);
        } catch (Exception ignored) {
        }

        String mensagem = "H0 :  βj = 0 for any j, j = 1, 2, 3, 4, 5 \nH1 : βj ≠ 0 for any j, j = 1, 2, 3, 4, 5";

        if (F > fa)
            mensagem = mensagem + "\nAs f0 = " + df.format(F) + " > " + df.format(fa) + ", we reject H0 \nThe linear regression model is significant";
        else
            mensagem = mensagem + "\nAs f0 = " + df.format(F) + " < " + df.format(fa) + ", we don't reject H0 \nThe linear regression model isn't significant";

        ArrayList<AnovaTable> anovaValues = new ArrayList<>();

        anovaValues.add(new AnovaTable("Regression", df.format(SQR), "" + k, df.format(MQR), df.format(F), df.format(fa)));
        anovaValues.add(new AnovaTable("Error", df.format(SQE), "" + (n - (k + 1)), df.format(MQE), "", ""));
        anovaValues.add(new AnovaTable("Total", df.format(SQT), "" + (n - 1), "", "", ""));
        anovaValues.add(new AnovaTable(mensagem, "", "", "", "", ""));

        return anovaValues;
    }
}
