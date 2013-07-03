/*
 * Menu właściciela
 * TODO menu na razie nic nie robi, należy dodać funkcjonalności
 */

package window.search;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import states.Owner;
import objects.Pizzeria;
import window.search.PizzeriaSearchForm;

public class OwnerMenu extends SearchWindow {
	
	//model, który okienko informuje co należy zrobić
	Owner model;
	
	public OwnerMenu(final Owner model) {
		this.model = model;
		final SearchWindow that = this;
		
		initComponents();
		
		refresh();
		
		pizzeriaAdvancedSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PizzeriaSearchForm pizzeriaSearchForm = 
					new PizzeriaSearchForm("Wyszukaj pizzerię", that, model);
				pizzeriaSearchForm.setVisible(true);
			}
		});
		pizzaAdvancedSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PizzaSearchForm pizzaSearchForm = 
					new PizzaSearchForm("Wyszukaj pizzę", that, model);
				pizzaSearchForm.setVisible(true);
			}
		});
		pizzeriaAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PizzeriaEditWindow pizzeriaEditWindow = new PizzeriaEditWindow(that, model);
				pizzeriaEditWindow.setVisible(true);
			}
		});
		pizzeriaEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pizzeria selected = (Pizzeria) that.pizzeriaList.getSelectedValue();
				if(selected!=null) {
					PizzeriaEditWindow pizzeriaEditWindow = new PizzeriaEditWindow(that, model);
					pizzeriaEditWindow.setData(selected);
					pizzeriaEditWindow.setVisible(true);
				}
			}
		});
		account1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				OwnerAccountWindow aw = new OwnerAccountWindow("Moje konto", model);
				aw.setVisible(true);
			}
		});
		
		account2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				OwnerAccountWindow aw = new OwnerAccountWindow("Moje konto", model);
				aw.setVisible(true);
			}
		});
		logout1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.logout();
			}
		});
        logout2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.logout();
			}
		});
	}
	
	public void refresh() {
		pizzeriaList.setListData(model.Pizzeria_GetAll());
		pizzaList.setListData(model.Oferta_GetAll());
	}
	
	private void initComponents() {
		super.pizzeriaToolBar.add(pizzeriaAdd);
		super.pizzaToolBar.add(pizzaAdd);
		super.pizzeriaToolBar.add(pizzeriaEdit);
		super.pizzaToolBar.add(pizzaEdit);
		super.pizzeriaToolBar.add(pizzeriaDelete);
		super.pizzaToolBar.add(pizzaDelete);
		super.pizzeriaToolBar.add(account1);
		super.pizzaToolBar.add(account2);
		super.pizzeriaToolBar.add(logout1);
        super.pizzaToolBar.add(logout2);
	}
	
	private JButton pizzeriaAdd = new JButton("Dodaj");
	private JButton pizzaAdd = new JButton("Dodaj");
	private JButton pizzeriaEdit = new JButton("Edytuj");
	private JButton pizzaEdit = new JButton("Edytuj");
	private JButton pizzeriaDelete = new JButton("Usuń");
	private JButton pizzaDelete = new JButton("Usuń");
	private JButton account1 = new JButton("Konto");
	private JButton account2 = new JButton("Konto");
	private JButton logout1 = new JButton("Wyloguj");
    private JButton logout2 = new JButton("Wyloguj");
}