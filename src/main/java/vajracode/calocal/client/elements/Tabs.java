package vajracode.calocal.client.elements;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.custom.CustomAnchor;
import gwt.material.design.client.custom.MaterialWidget;
import gwt.material.design.client.ui.ListItem;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.UnorderedList;

public class Tabs extends MaterialWidget implements HasValueChangeHandlers<Integer> {

	private static TabsUiBinder uiBinder = GWT.create(TabsUiBinder.class);
	interface TabsUiBinder extends UiBinder<Widget, Tabs> {}

	@UiField MaterialPanel contentPanel;
	@UiField UnorderedList tabPanel;
	@UiField HTMLPanel tabsContainer;
	
	private String color = "";
	private String textColor = "";
	private String indicatorColor = "";
	private String waves = "";
	private int tabIndex = 1;

	/**
	 * The tabs structure consists of an unordered list of tabs that have hashes corresponding to tab ids. Then when you click on each tab, only the container with the corresponding tab id will become visible.
	 */
	public Tabs() {
		initWidget(uiBinder.createAndBindUi(this));
		setWidth("100%");
	}
	
	@Override
	protected void onAttach() {
		super.onAttach();
		if (getTabPanelId() != null && getTabPanelId().length() > 0)
			return;
		tabPanel.getElement().setId(String.valueOf(System.currentTimeMillis()));
		String name = String.valueOf(hashCode());
		//int col = 12 / tabPanel.getWidgetCount();
		int index = tabPanel.getWidgetCount();
		for (Widget w : tabPanel) {
			if (w instanceof ListItem) {
				if(!waves.isEmpty()) ((ListItem) w).getWidget(0).getElement().addClassName("waves-effect waves-" + waves);
				if(!color.isEmpty()) w.getElement().addClassName(color);
				if(!textColor.isEmpty()) ((ListItem) w).getWidget(0).getElement().addClassName(textColor + "-text");
				((ListItem) w).getWidget(0).getElement().setAttribute("href", "#" + name + index);
				w.addStyleName("tab");
				index--;
			}
		}

		int indexC = contentPanel.getWidgetCount();
		for (Widget w : contentPanel) {
			w.getElement().setAttribute("id", name + indexC);
			indexC--;
		}
		initTabs(getTabPanelId());
		
		tabPanel.getElement().getStyle().clearWidth();
		for (Widget w : tabPanel)
			w.getElement().getStyle().clearWidth();
		
		changeIndicator(indicatorColor);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {			
			@Override
			public void execute() {
				updateSelection();				
//				Element e = tabPanel.getElement().getElementsByTagName(DivElement.TAG).getItem(tabPanel.getWidgetCount());
//				e.getStyle().setLeft(0, Unit.PX);
//				int right = tabPanel.getOffsetWidth() - tabPanel.getWidget(0).getOffsetWidth();
//				e.getStyle().setRight(right, Unit.PX);
//				GWT.log("indicator " + e.toString() + ": right = " + right + ", tabPanel.getOffsetWidth() = " + tabPanel.getOffsetWidth() + ", tabPanel.getWidget(0).getOffsetWidth() = " + tabPanel.getWidget(0).getOffsetWidth());
			}
		});		
	}
	
	/**
	 * Line Indicator on Tab Navigation
	 * @param color Color string
	 */
	public native void changeIndicator(String color)/*-{
		$wnd.jQuery( ".indicator" ).css( "background-color", color );
	}-*/;

	/**
	 * Adding each tab nav , must add also the Content if you add a tab nav item
	 * @param item Widget item
	 */
	@UiChild(tagname = "tab")
	public void addTab(final Widget item) {
		CustomAnchor a = new CustomAnchor();
		a.add(item);		
		final ListItem li = new ListItem(a);
		li.addDomHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				onTabActivated(tabPanel.getWidgetIndex(li) + 1);
			}
		}, ClickEvent.getType());
		tabPanel.add(li);
	}
	
	protected void onTabActivated(int index) {		
		tabIndex = index;
		GWT.log("tab " + tabIndex + " active");
		ValueChangeEvent.fire(this, tabIndex);		
	}

	@UiChild(tagname = "tabWidget")
	public void addTabWidget(final Widget w) {		
		tabsContainer.add(w);
	}

	/**
	 * Adding each tab content on the navigation
	 * @param item Widget content
	 */
	@UiChild(tagname = "content")
	public void addContent(final Widget item) {
		item.addStyleName("col s12");
		contentPanel.add(item);
	}

	/**
	 * Initialize the Material Tab
     * @param ulId Id of the list
	 */
	public static native void initTabs(String ulId)/*-{
		$wnd.jQuery(document).ready(function() {
			$wnd.jQuery('ul#' + ulId).tabs();
		});
	}-*/;
	
	
	/**
	 * Selecting the tab with id on the content 
	 * @param id Id of the tab
	 * @param ulId Id of the list
	 */
	private native void selectTab(String id, String ulId)/*-{
		 $wnd.jQuery(document).ready(function(){
		    $wnd.jQuery('ul#' + ulId).tabs('select_tab', id);
		 });
	}-*/;
	
	/**
	 * Get the color of the tab
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Setting the color of the tab
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Get the text color of the tab
	 */
	public String getTextColor() {
		return textColor;
	}

	/**
	 * Set the text color of the tab
	 */
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	/**
	 * Get the indicator underlined color
	 * @return Color string
	 */
	public String getIndicatorColor() {
		return indicatorColor;
	}

	/**
	 * Set the indicator underlined color
	 * @param indicatorColor Color string
	 */
	public void setIndicatorColor(String indicatorColor) {
		this.indicatorColor = indicatorColor;
	}

	/**
	 * Get the waves effect of the tab
	 */
	public String getWaves() {
		return waves;
	}

	/**
	 * Set the waves effect of the tab
	 */
	public void setWaves(String waves) {
		this.waves = waves;
	}


	/**
	 * @return the tabIndex
	 */
	public int getTabIndex() {
		return tabIndex;
	}


	/**
	 * @param tabIndex You can automatically set the tab index by passing the desired tab index number, starts from 1.
	 */
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
	
	public void updateSelection(){
		String id = contentPanel.getWidget(tabIndex).getElement().getId();
		GWT.log("selectTab(" + id + "," + getTabPanelId() + ")");
		selectTab(id, getTabPanelId());		
	}

	private String getTabPanelId() {
		return tabPanel.getElement().getId();
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	public void setTabAndUpdate(int index) {
		onTabActivated(index);
		updateSelection();		
	}
	
}