package tw.jimmy.lab;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Window;

public class TestViewModel {

	private String value;

	public String getValue() {
		return value;
	}

	@NotifyChange("value")
	public void setValue(String value) {
		this.value = value;
	}

	@Init
	public void Init() {
		this.value = "";
	}

	@Command("callPopup")
	public void callPopup(@ContextParam(ContextType.VIEW) Component view) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("value", getValue());
		params.put("parent", view);

		Window popup = (Window) Executions.createComponents("popup.zul", view,
				params);

		popup.doModal();

	}

	@Command("change")
	@NotifyChange("value")
	public void change(@BindingParam("newValue") String newValue) {
		setValue(newValue);
	}

	@Command("upload")
	public void upload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		UploadEvent upEvent = (UploadEvent) ctx.getTriggerEvent();

		System.out.println("uploading " + upEvent.getMedia().getName());
	}

}
