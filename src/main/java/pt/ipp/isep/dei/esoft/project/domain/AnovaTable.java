package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents a table entry for the ANOVA (Analysis of Variance) table.
 */
public class AnovaTable {
    private String fonteDeVariacao;
    private String somaQuad;
    private String grausLib;
    private String mediaQuad;
    private String estatisticaTeste;
    private String fSignifica;

    /**
     * Constructs an AnovaTable object with the provided information.
     *
     * @param fonteDeVariacao   The source of variation.
     * @param somaQuad          The sum of squares.
     * @param grausLib          The degrees of freedom.
     * @param mediaQuad         The mean squares.
     * @param estatisticaTeste  The test statistic.
     * @param fSignifica        The significance of the F-test.
     */
    public AnovaTable(String fonteDeVariacao, String somaQuad, String grausLib, String mediaQuad, String estatisticaTeste, String fSignifica) {
        this.fonteDeVariacao = fonteDeVariacao;
        this.somaQuad = somaQuad;
        this.grausLib = grausLib;
        this.mediaQuad = mediaQuad;
        this.estatisticaTeste = estatisticaTeste;
        this.fSignifica = fSignifica;
    }

    /**
     * Retrieves the source of variation.
     *
     * @return The source of variation.
     */
    public String getFonteDeVariacao() {
        return fonteDeVariacao;
    }

    /**
     * Retrieves the sum of squares.
     *
     * @return The sum of squares.
     */
    public String getSomaQuad() {
        return somaQuad;
    }

    /**
     * Retrieves the degrees of freedom.
     *
     * @return The degrees of freedom.
     */
    public String getGrausLib() {
        return grausLib;
    }

    /**
     * Retrieves the mean squares.
     *
     * @return The mean squares.
     */
    public String getMediaQuad() {
        return mediaQuad;
    }

    /**
     * Retrieves the test statistic.
     *
     * @return The test statistic.
     */
    public String getEstatisticaTeste() {
        return estatisticaTeste;
    }

    /**
     * Retrieves the significance of the F-test.
     *
     * @return The significance of the F-test.
     */
    public String getfSignifica() {
        return fSignifica;
    }
}
