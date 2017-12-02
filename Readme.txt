Steps to implement Google Translate Api

Step1: 
	Login to https://console.cloud.google.com

Step2:
	Create project and create credentials 
	
Step3:
	Set Environment Variable as GOOGLE_API_KEY 
	
Step4: 
	Create trail version to get output from this sample code else code will throw below error
	
	Exception in thread "main" com.google.cloud.translate.TranslateException: 403 Forbidden
	{
	  "error": {
		"code": 403,
		"message": "The request is missing a valid API key.",
		"errors": [
		  {
			"message": "The request is missing a valid API key.",
			"domain": "global",
			"reason": "forbidden"
		  }
		],
		"status": "PERMISSION_DENIED"
	  }
	}