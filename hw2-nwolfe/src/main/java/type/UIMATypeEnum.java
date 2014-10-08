package type;

/**
 * 
 * @author nwolfe
 * 
 *         This enum class takes type parameter strings which are normally hard coded in such
 *         methods as getConfigParameterValue() and makes them the output of an enum type.
 * 
 *         ...Why, you ask?
 * 
 *         Because it's dumb to have hard coded strings in critical classes of your pipeline... This
 *         is an annoying layer of indirection, but it pushes the problem of editing and changing
 *         strings down to the lowest possible level.
 *
 */
public enum UIMATypeEnum {

  HMM_MODEL("modelHMM"),
  INPUT_FILE("inputFile"),
  OUTPUT_FILE("outputFile");

  private final String parameter;

  private UIMATypeEnum(String param) {
    this.parameter = param;
  }

  public String getParam() {
    return this.parameter;
  }
}
