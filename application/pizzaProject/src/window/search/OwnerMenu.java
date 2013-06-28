/*
 * Menu właściciela
 * TODO menu na razie nic nie robi, należy dodać funkcjonalności
 */

package window.search;
import javax.swing.JButton;

import states.Owner;

public class OwnerMenu extends SearchWindow {
	
	//model, który okienko informuje co należy zrobić
	//(na razie nic nie umie) 
	Owner model;
	
	public OwnerMenu(final Owner model) {
		this.model = model;
		//final SearchWindow that = this;
		
		initComponents();
		/*
		pizzeriaList.setListData(model.Pizzeria_GetAll());
		pizzaList.setListData(model.Oferta_GetAll());
		
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
		*/
	}
	
	private void initComponents() {
		super.pizzeriaToolBar.add(pizzeriaReview);
		super.pizzaToolBar.add(pizzaReview);
		super.pizzeriaToolBar.add(pizzeriaEdit);
		super.pizzaToolBar.add(pizzaEdit);
		super.pizzeriaToolBar.add(pizzeriaDelete);
		super.pizzaToolBar.add(pizzaDelete);
		super.pizzeriaToolBar.add(account1);
		super.pizzaToolBar.add(account2);
	}
	
	private JButton pizzeriaReview = new JButton("Dodaj");
	private JButton pizzaReview = new JButton("Dodaj");
	private JButton pizzeriaEdit = new JButton("Edytuj");
	private JButton pizzaEdit = new JButton("Edytuj");
	private JButton pizzeriaDelete = new JButton("Usuń");
	private JButton pizzaDelete = new JButton("Usuń");
	private JButton account1 = new JButton("Konto");
	private JButton account2 = new JButton("Konto");
}