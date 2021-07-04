package kodlamaio.hrms.adapters.mernis;

import java.rmi.RemoteException;

import org.springframework.stereotype.Component;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Component("Mernis")
public class MernisServiceAdapter implements UserCheckService{

	@Override
	public boolean checkIfRealPerson(MernisValidation mernisValidation) {
		
		KPSPublicSoapProxy client = new KPSPublicSoapProxy();
		boolean result = false;
		
		try {
			result = client.TCKimlikNoDogrula(Long.parseLong(
					mernisValidation.getIdentityNumber()),
					mernisValidation.getFirstName().toUpperCase(), 
					mernisValidation.getLastName().toUpperCase(), 
					mernisValidation.getBirthYear().getYear());
		} catch (NumberFormatException | RemoteException exception ) {
			 exception.printStackTrace();
        }
        return result;
		
	}
}
		
//		String endpoint = "https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx";
//		
//		URL url = null;
//		
//		try {
//			url = URI.create(endpoint).toURL();
//		} catch (MalformedURLException e) {
//			return new ErrorResult();
//		}
//		
//		
//		KPSPublic service = new KPSPublic(url);
//		KPSPublicSoap port = service.getPort(KPSPublicSoap.class);
//		
//		
//		long nationalIdNoL = Long.parseLong(mernisValidation.getIdentityNumber());
//		
//		return new Result(port.TCKimlikNoDogrula(nationalIdNoL,
//				mernisValidation.getFirstName().toUpperCase(), mernisValidation.getLastName().toUpperCase(), 
//				mernisValidation.getBirthYear().getYear()));
//	}



