#Scadenzario Fatturazione

##Requirement

##Tools

La funzionalità è stata realizzata utilizzando le API java standard (**JDK7**) ad eccezione delle seguenti librerie di utility:

 - **apache commons-io**: utilizzata per la lettura e la scrittura dei files;
 - **apache commons-cli**: utilizzata per la creazione dell'interfaccia;

Il software è disponibile sottoforma di `executable-jar`assemblato con **Maven**. Per le istruzioni di utilizzo vedi [qui](#synopsys) 

##Implementation

Per l'implementazione dei servizi di business ho utilizzato un `Service Facade`  collegato ad una serie di `Handler`, in modo da ottenere un'interfaccia unica per l'accesso alla funzionalità, e l'incapsulamento della logica applicativa (*negli handler*).

Ogni  `Handler` implementa una specifica operazione, in modo che la logica applicativa sia disaccoppiata, rendendo più semplice eventuali modifiche e/o estensioni del software. 

![](https://raw.githubusercontent.com/fleanza74/DueRegister/1e7c64d7fd598cccf2ec0aaa3ceaea270d4f8281/invoiceService.png)

I dati sono stati modellati nella classe **Invoice** (`Entity Bean`)  e mantenuti nella classe **InvoiceRepo** (`in-memory list`)

![](https://raw.githubusercontent.com/fleanza74/DueRegister/master/invoiceModel.png)

###Additional classes:

Class                  | Description
---------------------- | -----------
**DueRegisterClient**  | CLI Client
**DueRegisterBuilder** | Builder per la costruzione del `Service Facade`
**Utils**              | Helper methods

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

##Synopsys
 
```
java -jar dueregister.jar -in <in-filepath> - out <out-filepath>
```

### Other
