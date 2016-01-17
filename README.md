#Scadenzario Fatturazione

##Tools

La funzionalità è stata realizzata utilizzando le API java standard (**JDK7**) ad eccezione delle seguenti librerie di utility:

 - **apache commons-io**: utilizzata per la lettura e la scrittura dei files;
 - **apache commons-cli**: utilizzata per la creazione dell'interfaccia;

Il software è disponibile sottoforma di `executable-jar`assemblato con **Maven**. Per le istruzioni di utilizzo vedi [qui](#synopsys) 

##Implementation

Per l'implementazione dei servizi di business ho utilizzato un `Service Facade`  collegato ad una serie di `Handler`, in modo da ottenere un'interfaccia unica per l'accesso alla funzionalità, e l'incapsulamento della logica applicativa (*negli handler*).

Ogni  `Handler` implementa una specifica operazione, in modo che la logica applicativa sia disaccoppiata, rendendo più semplice eventuali modifiche e/o estensioni del software. I dati sono stati modellati nella classe **Invoice** (`Entity Bean`).

di seguito un diagramma UML (non dettagliato) delle classi:

![uml](https://cloud.githubusercontent.com/assets/15958498/12379043/d435e7ea-bd51-11e5-867e-0e4a7efda043.png)

###Additional classes:

Class                  | Description
---------------------- | -----------
**DueRegisteeHelper**  | Helper methods
**DueRegisterClient**  | CLI Client
**DueRegisterBuilder** | Builder per la costruzione del `Service Facade`

### Code snippets:

>**Calcolo delle date** senza l'utilizzo di *Calendar.getActualMaximum*

```
String str_date="20/10/2015";
DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
Date date = df.parse(str_date);

Calendar cal = Calendar.getInstance();
cal.setTime(date);
System.out.println("Data Fattura: " + cal.getTime());

Calendar fm = (Calendar) cal.clone();
fm.add(Calendar.MONTH, 1);
fm.set(Calendar.DATE, 1);
fm.add(Calendar.DATE, -1);
System.out.println("Fine Mese: " + fm.getTime());

Calendar sg = (Calendar) cal.clone();
sg.add(Calendar.MONTH,2);
System.out.println("Sessanta Giorni: " + sg.getTime());
```

>**Ordinamento** senza l'utilizzo *del metodo sort di Collection o delle sue sottoclassi*

```
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
```

##Synopsys
 
```
java -jar dueregister-jar-with-dependencies.jar -in <in-filepath> - out <out-filepath>
```

