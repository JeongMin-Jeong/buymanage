    function getYmd10() {
        //yyyy-mm-dd 포맷 날짜 생성
        var d = new Date();
        var result = d.getFullYear() + "" + ((d.getMonth() + 1) > 9 ? (d.getMonth() + 1).toString() : "0" + (d.getMonth() + 1)) + "" + (d.getDate() > 9 ? d.getDate().toString() : "0" + d.getDate().toString());
        return result;
    }

    function randomString() {
        const chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZ'
        const stringLength = 4
        let randomstring = ''
        for (let i = 0; i < stringLength; i++) {
            const rnum = Math.floor(Math.random() * chars.length)
            randomstring += chars.substring(rnum, rnum + 1)
        }
        return randomstring
    }

    function displayRandomNum() {
        //프로덕트랜덤코드생성
        //var rnum = Math.random();
        var rnum = randomString();
        var cdate = getYmd10();
        var pcode = "P" + cdate + rnum;
        document.getElementById("pcode").value = pcode;
        alert("품목코드 생성 완료 하였습니다. : "+ pcode);
        //document.getElementById("pcode").disabled = true;
    }

    function displayRandomCNum() {
        //계약랜덤코드생성
        //var rnum = Math.random();
        var rnum = randomString();
        var cdate = getYmd10();
        var ccode = "C" + cdate + rnum;
        document.getElementById("ccode").value = ccode;
        alert("계약코드 생성 완료 하였습니다. : "+ ccode);
        //document.getElementById("ccode").disabled = true;
    }

    function displayRandomSNum() {
        //자재랜덤코드생성
        //var rnum = Math.random();
        var rnum = randomString();
        var cdate = getYmd10();
        var scode = "S" + cdate + rnum;
        document.getElementById("scode").value = scode;
        alert("자재코드 생성 완료 하였습니다. : "+ scode);
        //document.getElementById("scode").disabled = true;
    }

    function displayRandomONum() {
        //발주랜덤코드생성
        //var rnum = Math.random();
        var rnum = randomString();
        var cdate = getYmd10();
        var ocode = "O" + cdate + rnum;
        document.getElementById("ocode").value = ocode;
        alert("발주코드 생성 완료 하였습니다. : "+ ocode);
        //document.getElementById("ocode").disabled = true;
    }

    //발주등록시 계약정보 선택용 팝업
    function orderRegistOpenPopup() {
      	var width = 800;
       	var height = 600;
       	var left = (window.screen.width / 2) - (width/2);
       	var top = (window.screen.height / 4);
        var url = "/order/popup";
        var name = "계약선택 팝업"; // 팝업의 이름을 입력해줍니다.
       	//윈도우 속성 지정
       	var option = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=no, location=no, titlebar=yes';
        window.open(url, name, option); // 새로운 창이 뜨면서 팝업이 생성됩니다.
    }

    //발주등록시 계약정보 선택용 팝업
    function contractRegistOpenPopup() {
      	var width = 1200;
       	var height = 600;
       	var left = (window.screen.width / 2) - (width/2);
       	var top = (window.screen.height / 4);
        var url = "/contract/popup";
        var name = "품목코드선택 팝업"; // 팝업의 이름을 입력해줍니다.
       	//윈도우 속성 지정
       	var option = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=no, location=no, titlebar=yes';
        window.open(url, name, option); // 새로운 창이 뜨면서 팝업이 생성됩니다.
    }

    function returnParentWindows(pcode,pname,ptype1,ptype2){
        //alert("pcode : " + pcode + " / pname : " + pname);
        opener.document.getElementById('pcode').value = pcode;
        opener.document.getElementById('pname').value = pname;
        opener.document.getElementById('ptype1').value = ptype1;
        opener.document.getElementById('ptype2').value = ptype2;
        self.close();

    }
    function returnParentWindows2(ccode,pname,ptype1,ptype2,pprice,cpartnername){

        opener.document.getElementById('ccode').value = ccode;
        opener.document.getElementById('pname').value = pname;
        opener.document.getElementById('ptype1').value = ptype1;
        opener.document.getElementById('ptype2').value = ptype2;
        opener.document.getElementById('pprice').value = pprice;
        opener.document.getElementById('cpartnername').value = cpartnername;
        self.close();
    }


