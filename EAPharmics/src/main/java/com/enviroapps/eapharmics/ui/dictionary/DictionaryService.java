package com.enviroapps.eapharmics.ui.dictionary;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enviroapps.eapharmics.exception.EAPharmicsException;
import com.enviroapps.eapharmics.services.DictionaryImpl;
import com.enviroapps.eapharmics.ui.AbstractEAPharmicsService;
import com.enviroapps.eapharmics.vo.dictionary.DictionaryDetailVO;
import com.enviroapps.eapharmics.vo.dictionary.DictionaryMasterVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eapharmics/dictionary")
@RequiredArgsConstructor

public class DictionaryService extends AbstractEAPharmicsService {

	/**
	 * @param dictionaryCode
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getMultipleDictionaries")
	public List getMultipleDictionaries(@RequestParam(value = "dictionaryCode1") 
		String dictionaryCode1, @RequestParam(value = "dictionaryCode2") String dictionaryCode2,
		HttpServletRequest request)
		throws EAPharmicsException {
		checkSession();
		List<List> list = new ArrayList<List>();
		try {
			DictionaryImpl impl = new DictionaryImpl();
			list.add(impl.getDictionaryDetailsForCode(dictionaryCode1));
			list.add(impl.getDictionaryDetailsForCode(dictionaryCode2));
			return list;
		} catch (RuntimeException e) {
			logger.error(this, "getMultipleDictionaries", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
	}

	/**
	 * @param dictionaryCode
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getDictionaryMasterForCode")
	public DictionaryMasterVO getDictionaryMasterForCode(@RequestParam(value = "dictionaryCode") String dictionaryCode,
			HttpServletRequest request) 
		throws EAPharmicsException {
		checkSession();
		try {
			DictionaryImpl impl = new DictionaryImpl();
			DictionaryMasterVO voObject = impl.getDictionaryMasterForCode(dictionaryCode);
			return voObject;
		} catch (RuntimeException e) {
			logger.error(this, "getDictionaryMasterForCode", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
	}

	/**
	 * @param dictionaryCode
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getDictionaryDetails")
	public List<DictionaryDetailVO> getDictionaryDetails(@RequestParam(value = "dictionaryCode") String dictionaryCode,
			HttpServletRequest request) 
		throws EAPharmicsException {
		checkSession();
		List<DictionaryDetailVO> list = new ArrayList<DictionaryDetailVO>();
		try {
			DictionaryImpl impl = new DictionaryImpl();
			list = impl.getDictionaryDetailsForCode(dictionaryCode);			
		} catch (RuntimeException e) {
			logger.error(this, "getDictionaryDetails", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param dictionaryCode
	 * @return
	 * @throws EAPharmicsException
	 */
	@GetMapping("/getMultipleDictionaryDetails")
	public List<List> getMultipleDictionaryDetails(@RequestParam(value="dictionaryCodes") List<String> dictionaryCodes) 
		throws EAPharmicsException {
		checkSession();
		List<List> list = new ArrayList<List>();
		try {
			DictionaryImpl impl = new DictionaryImpl();
			for (Iterator iterator = dictionaryCodes.iterator(); iterator.hasNext();) {
				String code = (String) iterator.next();
				list.add(impl.getDictionaryDetailsForCode(code));
			}
		} catch (RuntimeException e) {
			logger.error(this, "getMultipleDictionaryDetails", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	@PostMapping("/updateDictionaryDetail")
	public List<DictionaryDetailVO> updateDictionaryDetail(@RequestBody DictionaryDetailVO voObject,
			HttpServletRequest req) 
	throws EAPharmicsException {
		checkSession(req);
		List<DictionaryDetailVO> list = new ArrayList<DictionaryDetailVO>();
		try {
			DictionaryImpl impl = new DictionaryImpl();
			impl.updateDictionaryDetail(voObject, getUser(req));
			list = impl.getDictionaryDetailsForCode(voObject.getDictionaryCode());		
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "updateDictionaryDetail", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	@PostMapping("/updateDictionaryDetails")
	public List updateDictionaryDetails(@RequestParam String dictionaryCode, @RequestBody List<DictionaryDetailVO> dictionaryDetails,
			HttpServletRequest request) 
	throws EAPharmicsException {
		checkSession();
		
//		String dictionaryCode = new ObjectMapper().convertValue(payload.get("dictionaryCode"), String.class);
//	    List dictionaryDetails = new ObjectMapper().convertValue(payload.get("dictionaryDetails"), List<FictionaryDetailVO>.class);

		//List <DictionaryDetailVO> dictionaryDetails = Arrays.asList((DictionaryDetailVO[]) dictionaryDetailsArr);
		if (dictionaryDetails == null || dictionaryDetails.size() == 0) {
			return null;
		}
		try {
			DictionaryImpl impl = new DictionaryImpl();
			impl.updateDictionaryDetails(dictionaryDetails, getUser());
			return impl.getDictionaryDetailsForCode(dictionaryCode);		
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "updateDictionaryDetail", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
	}

	/**
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	@PostMapping("/addDictionaryDetail")
	public List<DictionaryDetailVO> addDictionaryDetail(@RequestBody DictionaryDetailVO voObject,
		HttpServletRequest req) 
	throws EAPharmicsException {
		checkSession(req);
		List<DictionaryDetailVO> list = new ArrayList<DictionaryDetailVO>();
		try {
			DictionaryImpl impl = new DictionaryImpl();
			impl.createDictionaryDetail(voObject, getUser(req));
			list = impl.getDictionaryDetailsForCode(voObject.getDictionaryCode());		
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "addDictionaryDetail", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		//setContext(user);
		return list;
	}

	/**
	 * @param dictionaryCode
	 * @return
	 * @throws EAPharmicsException	 * 
	 * Not used
	 */
	public List<DictionaryDetailVO> getAllDictionaryDetails() 
		throws EAPharmicsException {
		checkSession();
		List<DictionaryDetailVO> list = new ArrayList<DictionaryDetailVO>();
		try {
			DictionaryImpl impl = new DictionaryImpl();
			list = impl.getAllDictionaryDetails();			
		} catch (RuntimeException e) {
			logger.error(this, "getDictionaryDetails", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
		}
		return list;
	}

	/**	
	 * @param voObject
	 * @return
	 * @throws EAPharmicsException
	 */
	@PostMapping("/deleteDictionaryDetail")
	public List<DictionaryDetailVO> deleteDictionaryDetail(@RequestBody DictionaryDetailVO voObject) 
	throws EAPharmicsException {
		checkSession();
		List<DictionaryDetailVO> list = new ArrayList<DictionaryDetailVO>();
		try {
			DictionaryImpl impl = new DictionaryImpl();
			impl.deleteDictionaryDetail(voObject.getEaDictionaryDetailId(), getUser());
			list = impl.getDictionaryDetailsForCode(voObject.getDictionaryCode());		
		} catch (EAPharmicsException e) {
			if (e.getCause() instanceof EAPharmicsException) {
				throw (EAPharmicsException) e.getCause();
			}
			throw e;
		} catch (RuntimeException e) {
			logger.error(this, "updateDictionaryDetail", e);
			throw new EAPharmicsException("Unable to process request. Please contact support.");
		}
		return list;
	}
	
	
/* Added By Ramya Srinivasan*/
   
   /**
 * @return
 * @throws EAPharmicsException
 */
@GetMapping("/getAllDictionary")
public List<DictionaryDetailVO> getAllDictionary() 
   throws EAPharmicsException {
   checkSession();
   List<DictionaryDetailVO> list = new ArrayList<DictionaryDetailVO>();
   try {
      DictionaryImpl impl = new DictionaryImpl();
      list = impl.getAllDictionary();       
   } catch (RuntimeException e) {
      logger.error(this, "getDictionaryDetails", e);
      throw new EAPharmicsException("Unable to process request. Please contact support.\n" + e.getMessage());
   }
   return list;
}
	
	
	
	

}
