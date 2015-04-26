package multidbskeywordsearchgserverapi;



import edu.pitt.sis.infsci2711.multidbs.utils.PropertiesManager;

public class testrest {
	public void main(final String[] args){
		String metastoreRegister = PropertiesManager.getInstance().getStringProperty("metastore.rest.getAllDatasources", "");
		System.out.println(metastoreRegister);
	}
	
	
}
