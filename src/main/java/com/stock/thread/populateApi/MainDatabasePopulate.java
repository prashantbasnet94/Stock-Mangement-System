package com.stock.thread.populateApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.stock.dao.StockDaoOperator;

import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

@Aspect
@Component
public class MainDatabasePopulate  extends TimerTask implements Runnable{
	@Override
	public void run() {
	
		

		final IEXTradingClient iexTradingClient = IEXTradingClient.create();
		
		Quote quote ;
		
		

		 	String myString= "WMT,XOM,MCK,UNH,CVS,GM,F,T,GE,ABC,VZ,CVX,COST,FNMA,KR,AMZN,HPQ,CAH,ESRX,JPM,BA,MSFT,BAC,WFC,HD,C,PSX,IBM,VLO,ANTM,PG,GOOGL,CMCSA,TGT,JNJ,MET,ADM,MPC,FMCC,PEP,UTX,AET,LOW,UPS,AIG,PRU,INTC,HUM,DIS,CSCO,PFE,DOW,SYY,FDX,CAT,LMT,KO,HCA,ETE,TSN,AAL,DAL,JCI,BBY,MRK,GS,HON,ORCL,MS,CI,UAL,ALL,INTL,CHS,AXP,GILD,GD,TJX,COP,NKE,INT,MMM,MDLZ,EXC,FOXA,DE,TSO,TWX,DD,AVT,M,EPD,TRV,PM,RAD,TECD,MCD,QCOM,SHLD,COF,DUK,HAL,NOC,ARW,RTN,PAGP,ABBV,CNC,CYH,ARNC,IP,EMR,UNP,AMGN,USB,SPLS,DHR,WHR,AFL,AN,PGR,ABT,DG,THC,LLY,LUV,PAG,MAN,KSS,SBUX,PCAR,CMI,MO,XRX,KMB,HIG,KHC,LEA,FLR,ACM,FB,JBL,CTL,SVU,GIS,SO,NEE,TMO,AEP,PCG,NGL,BMY,G,NUE,PNC,MU,CL,FCX,CAG,GPS,BHI,BK,DLTR,WFM,PPG,GPC,IEP,PFGC,OMC,DISH,FE,MON,AES,KMX,NRG,WDC,MAR,ODP,JWN,KMI,ARMK,DVA,MOH,WCG,CBS,V,LNC,ECL,K,CHRW,TXT,L,ITW,SNX,HFC,LAKE,DVN,PBF,YUM,TXN,CDW,WM,MMC,CHK,PH,OXY,JCP,ED,CTSH,VFC,AMP,LB,JEC,PFG,ROST,BBBY,CSX,LVS,LUK,D,X,LLL,EIX,ADP,FDC,BLK,WRK,VOYA,SHW,HLT,RRD,SWK,XEL,MUSA,CBG,DHI,EL,PX,BIIB,STT,UNM,RAI,GPI,HSIC,HRI,NSC,RGA,PEG,BBT,DTE,AIZ,GLP,HUN,BDX,SRE,AZO,NAV,DFS,QVCA,GWW,BAX,SYK,APD,WNR,UHS,OMI,CHTR,AAP,MA,AMAT,EMN,SAH,ALLY,CST,EBAY,LEN,GME,RS,HRL,CELG,GNW,PYPL,PCLN,MGM,ALV,FNF,RSG,GLW,UNVR,MOS,CORE,HDS,CCK,EOG,VRTV,APC,LH,FOXA,STI,CAR,LVLT,TEN,UNFI,DF,CPB,MHK,BWA,PVH,BLL,ORLY,ES,BEN,MAS,LAD,KKR,OKE,NEM,PPL,SPTN,PWR,XPO,RL,IPG,STLD,WCC,DGX,BSX,AGCO,FL,HSY,CNP,WMB,DKS,LYV,WRB,LKQ,AVP,DRI,KND,WY,CASY,SEE,FITB,DOV,HII,NFLX,DDS,EME,JONE,AKS,UGI,EXPE,CRM,TRGP,APA,SPR,EXPD,AXE,FIS,ABG,HES,R,TEX,SYMC,SCHW,CPN,CMS,ADS,JBLU,DISCA,TRN,SANM,NCR,FTI,ERIE,ROK,DPS,IHRT,TSCO,JBHT,CMC,OI,AFG,NTAP,OSK,AEE,AMRK,BKS,DAN,STZ,LPNT,ZBH,HOG,PHM,NWL,AVY,JLL,WEC,MRO,TA,URI,HRG,ORI,WIN,DK,PKG,Q,HBI,RLGY,MAT,MSI,SJM,RF,CE,CLX,INGR,GEN,BTU,ALK,SEB,FTR,APH,WYN,KELYA,WU,VC,AJG,HST,ASH,NSIT,MKL,ESND,OC,SPGI,RJF,NI,ABM,CFG,BAH,SPGI,UFS,COL,LRCX,FISV,SE,NAVI,BIG,TDS,FAF,NVR,CINF,BURL";
		 	//String myString ="A";
		    //Remove whitespace and split by comma 
		    List<String> finalString= Arrays.asList(myString.split("\\s*,\\s*"));

	
		
		    List l = new ArrayList();
		    for(String i: 	finalString)
		        l.add(i);

		    Collections.shuffle(l);

		   
		    for (int i = 0; i < finalString.size(); i++) {
		     
		        
					quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
					        .withSymbol((String) l.get(i))
					        .build());
			     
			    	StockDaoOperator.save(0,quote.getCompanyName(), quote.getSymbol(), quote.getLow(), quote.getHigh(), quote.getChangePercent(),
							quote.getLatestVolume(), quote.getMarketCap(), quote.getOpen()) ;
				
				
		    
		    
		    }
		
	
		
		
	     
		
 		
	}
 
	
}
