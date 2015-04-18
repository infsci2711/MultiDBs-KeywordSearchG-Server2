package multidbskeywordsearchgserverapi;

import edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.server.PropertiesManager;

import java.io.IOException;

public class testrest {
	public void main(String[] args){
		String metastoreRegister = PropertiesManager.getInstance().getStringProperty("metastore.rest.getAllDatasources", "");
		System.out.println(metastoreRegister);
	}
	
	
}
