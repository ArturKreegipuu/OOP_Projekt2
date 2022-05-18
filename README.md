# OOP_Projekt2

OOPi projektile GUI juurde


Autorid: Artur Kreegipuu & Carl-Christjan Bogoslovski

Tegemist on arvutimänguga, mille eesmärgiks on valida õigeid relvi, et vastane ületada enne, kui mängija elud otsa saavad. Mängijalt küsitakse nime ja raskusastet, milleks on kas kerge, keskmine või raske. Vastasele genereeritakse automaatselt nimi. Võimalik valida on viie relva vahel: vibu, ragulka, miinipilduja, javelin ja sapöörilabidas. Nüüdseks on lisatud on ka GUI.

Juhised: 1. nimeks võib panna ükskõik, mida hing ihkab 
         2. tasemeks tuleb valida üks kolmest antud valikutest
         3. relvaks tuleb valida ühe viiest antud valikutest
         4. kui kasutada sapöörilabidat valel distantsil, siis kaob see relvade hulgast
         5. relva moona otsasaamisel saab vastavalt mängu käigus antud juhistele juurde     
            lisada

Klassid: Ülemklassid Relvastus, Vastane
         1. Relvastus - klassist saadakse kätte relvade tabavused, moonakoguse, saab lisada
            moona ja rünnata, olemas ka toString meetod
         2. Vastane - saab kätte vastase kauguse mängijast, kas vastane tabab, kui suurt 
            kahju vastane teeb, vastase elud ja vajadusel neid muuta, kui mängija peaks teda 
            tabama, olemas ka toString meetod
         
         Mängija - saab kätte mängija nime ja elud, vajadusel saab elusid muuta, kui vastane peaks 
                   mängijat tabama
            
         Alamklassid relvadele - saab kätte relvade tabavused, moona koguse ja vajadusel saab lisada
         Alamklassid vastastele - saab kätte vastaste tabavused
         Mõlemad kasutavad ilmselgelt ülemklassi meetodeid 
         
         Peaklass - scannerid mängija/taseme/relva/moona valimiseks/lisamiseks, while tsükkel kuni 
         elude lõppemiseni, relvade list, GUI jaoks vastavad funktsioonid
         
Protsess: 1. esimese projekti edasiarenduse brainstormimine
          2. GUI loomine
          3. testimine koodi sammu-sammu haaval jooksutades
          
          
          
Hinnang: GUI sai lisatud, kuid kuna ühel projekti koostajatest oli probleeme JavaFx tööle saamisega, siis pidi arendus toimuma ainult ühest arvutist, et  oleks võimalik koodi testida. GUI jäi poolikuks ajapuuduse tõttu. Näiteks mängu taseme valimiseks kõik nupud ei töötanud. Paraku pidi üks koostajatest minema Kaitseväe õppusele ning seetõttu arenduse hoog jäi pidama.
         

          
