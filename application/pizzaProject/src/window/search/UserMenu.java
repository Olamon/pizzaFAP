package window.search;

import javax.swing.JButton;

import window.search.SearchWindow;
import window.search.PizzeriaSearchForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import states.User;

public class UserMenu extends SearchWindow {
	
	//model, który okienko informuje co należy zrobić
	//(na razie nic nie umie) 
	User model;
	
	public UserMenu(final User model) {
		this.model = model;
		final SearchWindow that = this;
		
		initComponents();
		
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
	}
	
	private void initComponents() {
		super.pizzeriaToolBar.add(pizzeriaReview);
		super.pizzaToolBar.add(pizzaReview);
		super.pizzeriaToolBar.add(account1);
		super.pizzaToolBar.add(account2);
	}
	
	private JButton pizzeriaReview = new JButton("Oceń");
	private JButton pizzaReview = new JButton("Oceń");
	private JButton account1 = new JButton("Konto");
	private JButton account2 = new JButton("Konto");
}
