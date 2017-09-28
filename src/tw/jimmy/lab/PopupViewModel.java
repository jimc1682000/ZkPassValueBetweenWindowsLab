package tw.jimmy.lab;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;

public class PopupViewModel {
	
	private String value;
	@SuppressWarnings("unused")
	private Binder parBinder;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Init
	public void Init(@ExecutionArgParam("value") String val , @ExecutionArgParam("parent") Component par) {
		this.parBinder = (Binder) par.getAttribute("binder");
		setValue(val);
	}

	@Command("change")
	public void change(@ContextParam(ContextType.VIEW) Component view) {
		
		Binder bind = (Binder) view.getParent().getAttribute("binder");
		if (bind == null)
			return;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("newValue", getValue());

		// this.parBinder.postCommand("change", params);
		bind.postCommand("change", params);
		
		view.detach();
	}
}

