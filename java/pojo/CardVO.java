package pojo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
public class CardVO
{
	public String successful;

	public void setSuccess(String success) {
	this.successful = success;
	}
	public String getSuccess() {
	return successful;
	}
}
