import javax.swing.JFrame;


public abstract class SearchForm extends JFrame{
	
	public SearchForm(String s){
		super(s);
	}
	
	abstract void createForm();
}
