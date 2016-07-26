package com.example.myapexapp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.annotation.Stateless;
import com.datatorrent.common.util.BaseOperator;

/**
 * Writes tuples to stdout of the container.
 * <p>
 * Mainly to be used for debugging. Users should be careful to not have this
 * node listen to a high throughput stream<br>
 * <br>
 * </p>
 * 
 * @displayName Console Output
 * @category Output
 * @tags output operator
 *
 * @since 0.3.2
 */
@Stateless
public class XmlRulesOutputOperator extends BaseOperator {
	private static final Logger logger = LoggerFactory
			.getLogger(XmlRulesOutputOperator.class);
	
	public void printXmlOutput() {
		  Customer customer = new Customer();
		  customer.setId(100);
		  customer.setName("mkyong");
		  customer.setAge(29);
		  ArrayList<String> l = new ArrayList<String>();
		  l.add("one");
		  HashMap<String, ArrayList<String>> m = new HashMap<String, ArrayList<String>>();
		  m.put("phone-add", l);
		  customer.setMap(m);
		  customer.setRuleOutput("MyName");

		  try {

			  CustomerOutputList customerList = new CustomerOutputList();
			  customerList.getL().add(customer);
			File file = new File("C:\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(CustomerOutputList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			//jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(customerList, System.out);

		      } catch (JAXBException e) {
			e.printStackTrace();
		      }

		}

	/**
	 * This is the input port which receives the tuples that will be written to
	 * stdout.
	 */
	public final transient DefaultInputPort<Object> input = new DefaultInputPort<Object>() {
		@Override
		@SuppressWarnings("UseOfSystemOutOrSystemErr")
		public void process(Object t) {
			String s;
			if (stringFormat == null) {
				s = t.toString();
			} else {
				s = String.format(stringFormat, t);
			}
			if (!silent) {
				printXmlOutput();
				//System.out.println("Hello:" + s);
			}
			if (debug) {
				logger.info("Hello:" + s);
			}
		}
	};
	public boolean silent = false;

	/**
	 * @return the silent
	 */
	public boolean isSilent() {
		return silent;
	}

	/**
	 * @param silent
	 *            the silent to set
	 */
	public void setSilent(boolean silent) {
		this.silent = silent;
	}

	/**
	 * When set to true, tuples are also logged at INFO level.
	 */
	private boolean debug;
	/**
	 * A formatter for {@link String#format}
	 */
	private String stringFormat;

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getStringFormat() {
		return stringFormat;
	}

	public void setStringFormat(String stringFormat) {
		this.stringFormat = stringFormat;
	}
}
