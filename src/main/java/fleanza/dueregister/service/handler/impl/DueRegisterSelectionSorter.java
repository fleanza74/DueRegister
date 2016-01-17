package fleanza.dueregister.service.handler.impl;

import java.util.Arrays;
import java.util.List;

import fleanza.dueregister.model.Invoice;
import fleanza.dueregister.service.handler.DueRegisterSorter;

/**
 * @author fleanza
 */
public class DueRegisterSelectionSorter implements DueRegisterSorter {

	@Override
	public List<Invoice> sort(List<Invoice> source) {

		Invoice[] array = (Invoice[])source.toArray(new Invoice[0]);

		int i,j,l;
		for(i = 0; i < array.length - 1; i++) {
			for(j = i + 1, l = i; j < array.length; j++) {
				if(array[j].getDueDate().before(array[l].getDueDate())) {
					l = j;
				}
				Invoice tmp = array[l];
				array[l] = array[i];
				array[i] = tmp;
			}
		}

		return Arrays.asList(array);
	}
}
