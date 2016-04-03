package vajracode.calocal.client.elements;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Random;

import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.constants.Orientation;
import gwt.material.design.client.ui.MaterialInput;
import gwt.material.design.client.ui.MaterialPanel;

//@formatter:off
/**
* Material Time Picker -  provide a simple way to select a single value from a pre-determined set.
* <h3>UiBinder Usage:</h3>
* <pre>
*{@code <m:MaterialTimePicker placeholder="Time Arrival" />
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#pickers">Material Pickers</a>
* @author kevzlou7979
* @author Ben Dol
*/
//@formatter:on
public class TimePicker extends MaterialWidget implements HasError, HasPlaceholder, HasOrientation {

    MaterialPanel panel = new MaterialPanel();
    MaterialInput input = new MaterialInput();

    private String time, id;
    private String placeholder;
    private boolean autoClose;
    private Orientation orientation = Orientation.PORTRAIT;

    private MaterialLabel lblError = new MaterialLabel();

    private final ErrorMixin<TimePicker, MaterialLabel> errorMixin = new ErrorMixin<>(this, lblError, input);

    public TimePicker() {
        super(Document.get().createElement("div"));
        panel.add(lblError);
        input.setType(InputType.TEXT);
        input.setId(id = String.valueOf(Random.nextInt()));
        panel.add(input);
        add(panel);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        input.getElement().setAttribute("type", "text");
        initTimePicker();
    }

    public void reset() {
        setTime("");
        clearErrorOrSuccess();
    }

    /**
     * @return the time
     */
    public String getTime() {
        return input.getElement().getAttribute("value");
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
        input.getElement().setAttribute("value", time.toUpperCase());
    }

    public boolean isAutoClose() {
        return autoClose;
    }

    public void setAutoClose(boolean autoClose) {
        this.autoClose = autoClose;
    }

    /**
     * @return the placeholder
     */
    @Override
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * @param placeholder the placeholder to set
     */
    @Override
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        input.getElement().setAttribute("placeholder", placeholder);
    }

    /**
     * @return the orientation
     */
    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * @param orientation the orientation to set : can be Horizontal or Vertical
     */
    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public void setError(String error) {
        errorMixin.setError(error);
    }

    @Override
    public void setSuccess(String success) {
        errorMixin.setSuccess(success);
    }

    @Override
    public void clearErrorOrSuccess() {
        errorMixin.clearErrorOrSuccess();
    }

    public void initTimePicker() {
        initTimePicker(input.getElement(), getOrientation().getCssName(), isAutoClose());
    }

    protected native void initTimePicker(Element e, String orientation, boolean autoClose) /*-{
        $wnd.jQuery(e).lolliclock({
            autoclose: autoClose,
            orientation: orientation
        });
        $wnd.jQuery(e).blur();
    }-*/;
}