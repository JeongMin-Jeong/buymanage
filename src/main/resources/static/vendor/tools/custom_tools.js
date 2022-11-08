
    //yyyy-mm-dd 포맷 날짜 생성
    function getYmd10() {
        var d = new Date();
        //var result = d.getFullYear();
        var result = d.getFullYear() + "" + ((d.getMonth() + 1) > 9 ? (d.getMonth() + 1).toString() : "0" + (d.getMonth() + 1)) + "" + (d.getDate() > 9 ? d.getDate().toString() : "0" + d.getDate().toString());
        return result;
    }

    //랜덤코드생성 알파멧 2자리, 숫자 2자리 총 4자리
    function randomString() {
        const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        const stringLength = 2;
        let randomstring = '';
        for (let i = 0; i < stringLength; i++) {
            const rnum = Math.floor(Math.random() * chars.length);
            randomstring += chars.substring(rnum, rnum + 1);
        }
        chars2 = '0123456789';
        stringLength2 = 2;
        let randomstring2 = '';
        for (let i = 0; i < stringLength2; i++) {
            const rnum = Math.floor(Math.random() * chars2.length);
            randomstring2 += chars2.substring(rnum, rnum + 1);
        }
        return randomstring+randomstring2;
    }

    //폼목생성이후 계약및 발주 코드 생성시 품목토드  +2자리 랜덤 숫자 추가
    function randomString2() {
        const chars = '0123456789';
        const stringLength = 2;
        let randomstring = '';
        for (let i = 0; i < stringLength; i++) {
            const rnum = Math.floor(Math.random() * chars.length);
            randomstring += chars.substring(rnum, rnum + 1);
        }
        return randomstring;
    }

    //품목랜덤코드생성
    function displayRandomNum() {
        //var rnum = Math.random();
        var target1 = document.getElementById("category");
        var target2 = document.getElementById("categorySelect");
        var category1 = target1.selectedIndex;
        var category2 = target2.selectedIndex;
        //alert('선택된 옵션 value 값=' + target1.options[target1.selectedIndex].value);     // 옵션 value 값
        //alert('선택된 옵션 value 값=' + target2.options[target2.selectedIndex].value);     // 옵션 value 값
        //alert('선택된 옵션 text 값=' + target2.options[target2.selectedIndex].text);     // 옵션 text 값
        //alert('선택된 옵션 value 값=' + target2.options[target2.selectedIndex].value);     // 옵션 value 값
        var rnum = randomString();
        var cdate = getYmd10();
        var pcode = "P" + cdate + "P" + category1 + "C" + category2 + rnum;
        document.getElementById("pcode").value = pcode;
        alert("품목코드 생성 완료 하였습니다. : "+ pcode);
        //document.getElementById("pcode").disabled = true;
    }

    //계약랜덤코드생성
    function displayRandomCNum(pcode) {
        //var rnum = Math.random();
        //var cdate = getYmd10();
        var rnum = randomString2();

        var ccode = pcode.replace('P','C') + rnum;
        //document.getElementById("ccode").value = ccode;
        opener.document.getElementById('ccode').value = ccode;
        //alert("계약코드 생성 완료 하였습니다. : "+ ccode);
        //document.getElementById("ccode").disabled = true;
    }

    //자재랜덤코드생성
    function displayRandomSNum() {
        //var rnum = Math.random();
        var target1 = document.getElementById("category");
        var target2 = document.getElementById("categorySelect");
        var category1 = target1.selectedIndex;
        var category2 = target2.selectedIndex;
        //alert('선택된 옵션 value 값=' + target1.options[target1.selectedIndex].value);     // 옵션 value 값
        //alert('선택된 옵션 value 값=' + target2.options[target2.selectedIndex].value);     // 옵션 value 값
        //alert('선택된 옵션 text 값=' + target2.options[target2.selectedIndex].text);     // 옵션 text 값
        //alert('선택된 옵션 value 값=' + target2.options[target2.selectedIndex].value);     // 옵션 value 값
        var rnum = randomString();
        var cdate = getYmd10();
        //var scode = "P" + cdate + rnum;
        var scode = "P" + cdate + "P" + category1 + "C" + category2 + rnum;
        document.getElementById("scode").value = scode;
        //alert("품목코드(자재) 생성 완료 하였습니다. : "+ scode);
        //document.getElementById("scode").disabled = true;
    }

    //발주랜덤코드생성
    function displayRandomONum(ccode) {
        //var rnum = Math.random();
        //var cdate = getYmd10();
        var rnum = randomString2();
        //var ocode = "O" + cdate + rnum;
        var ocode = ccode.replace('C','O') + rnum;
        opener.document.getElementById('ocode').value = ocode;
        //document.getElementById("ocode").value = ocode;
        //alert("발주코드 생성 완료 하였습니다. : "+ ocode);
        //document.getElementById("ocode").disabled = true;
    }

    //발주등록시 계약정보 선택용 팝업
    function orderRegistOpenPopup() {
      	var width = 1200;
       	var height = 600;
       	var left = (window.screen.width / 2) - (width/2);
       	var top = (window.screen.height / 4);
        var url = "/order/popup?page=1&ctype1=%EA%B3%84%EC%95%BD%EC%99%84%EB%A3%8C&ctype2=&keyword=";
        var name = "계약선택 팝업"; // 팝업의 이름을 입력해줍니다.
       	//윈도우 속성 지정
       	var option = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=no, location=no, titlebar=yes';
        window.open(url, name, option); // 새로운 창이 뜨면서 팝업이 생성됩니다.
    }

    //계약등록시 품목 선택용 팝업
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

    //계약등록시 납품업체 선택용 팝업
    function partnerRegistOpenPopup() {
        var width = 1200;
        var height = 600;
        var left = (window.screen.width / 2) - (width/2);
        var top = (window.screen.height / 4);
        var url = "/contract/partnerPopup";
        var name = "납품업체 팝업"; // 팝업의 이름을 입력해줍니다.
        //윈도우 속성 지정
        var option = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=no, location=no, titlebar=yes';
        window.open(url, name, option); // 새로운 창이 뜨면서 팝업이 생성됩니다.
    }

    //조달계획등록시 품목 선택용 팝업
    function transferPlanRegistOpenPopup() {
        var width = 1200;
        var height = 600;
        var left = (window.screen.width / 2) - (width/2);
        var top = (window.screen.height / 4);
        var url = "/transferPlan/popup";
        var name = "품목코드선택 팝업"; // 팝업의 이름을 입력해줍니다.
        //윈도우 속성 지정
        var option = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=no, location=no, titlebar=yes';
        window.open(url, name, option); // 새로운 창이 뜨면서 팝업이 생성됩니다.
    }

    //거래명세서 클릭시 프린트화면 팝업
    function tradePrintOpenPopup() {
        var width = 1400;
        var height = 700;
        var left = (window.screen.width / 2) - (width/2);
        var top = (window.screen.height / 4);
        var str = document.querySelector('input[name="radio2"]:checked').value;
        var check = str.split("/");
        var cno = check[0];
        var ocode = check[1];
        console.log(cno);
        console.log(ocode);
        var url = "/contract/tradeprint?cno=" + cno + "&ocode=" + ocode;
        var name = "거래명세서 출력"; // 팝업의 이름을 입력해줍니다.
        //윈도우 속성 지정
        var option = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=no, location=no, titlebar=yes';
        window.open(url, name, option); // 새로운 창이 뜨면서 팝업이 생성됩니다.
    }

    //계약코드 생성
    function returnParentWindows(pcode,pname,ptype1,ptype2){
        //alert("pcode : " + pcode + " / pname : " + pname);
        opener.document.getElementById('pcode').value = pcode;
        opener.document.getElementById('pname').value = pname;
        opener.document.getElementById('ptype1').value = ptype1;
        opener.document.getElementById('ptype2').value = ptype2;
        displayRandomCNum(pcode);
        self.close();
    }

    //계약등록시 품목코드를 팝업창에서 선택하면 부모창에 폼목코드,이름 자동입력 및 계약코드 랜덤 자동생성 입력??
    function returnParentWindows2(ccode,cno,pcode,pname,ptype1,ptype2,pprice,cpartnername){
        opener.document.getElementById('ccode').value = ccode;
        opener.document.getElementById('cno').value = cno;
        opener.document.getElementById('pcode').value = pcode;
        opener.document.getElementById('pname').value = pname;
        opener.document.getElementById('ptype1').value = ptype1;
        opener.document.getElementById('ptype2').value = ptype2;
        opener.document.getElementById('pprice').value = pprice;
        opener.document.getElementById('cpartnername').value = cpartnername;
        displayRandomONum(ccode);
        self.close();
    }

    function returnParentWindows3(cpartnerno,cpartnername,cpartnerceo,cpartneraddr,cpartnerphone,cpartnerfax){
        opener.document.getElementById('cpartnerno').value = cpartnerno;
        opener.document.getElementById('cpartnername').value = cpartnername;
        opener.document.getElementById('cpartnerceo').value = cpartnerceo;
        opener.document.getElementById('cpartneraddr').value = cpartneraddr;
        opener.document.getElementById('cpartnerphone').value = cpartnerphone;
        opener.document.getElementById('cpartnerfax').value = cpartnerfax;
        self.close();
    }

    function returnParentWindows4(pcode,pname){
        opener.document.getElementById('pcode').value = pcode;
        opener.document.getElementById('pname').value = pname;
        self.close();
    }

    //발주 수정에서 발주상태 변경시 input값 변경
    function changeOrderStatus(selectvalue){
        document.getElementById("ostate").value = selectvalue;
    }

    function inspectionRegistOpenPopup(mode){
        var width = 1200;
        var height = 600;
        var left = (window.screen.width / 2) - (width/2);
        var top = (window.screen.height / 4);
        var ono = document.getElementById('ono').value;
        var mode = mode;
        var url = "/inspection/"+mode+"?ono="+ono;
        var name = "검수정보 등록"; // 팝업의 이름을 입력해줍니다.
        //윈도우 속성 지정
        var option = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=no, location=no, titlebar=yes';
        window.open(url, name, option); // 새로운 창이 뜨면서 팝업이 생성됩니다.
    }

    function orderComplete(mode){
        var width = 1200;
        var height = 600;
        var left = (window.screen.width / 2) - (width/2);
        var top = (window.screen.height / 4);
        var ono = document.getElementById('ono').value;
        var mode = mode;
        var url = "/inspection/"+mode+"?ono="+ono;
        var name = "검수정보 등록"; // 팝업의 이름을 입력해줍니다.
        //윈도우 속성 지정
        var option = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=no, location=no, titlebar=yes';
        window.open(url, name, option); // 새로운 창이 뜨면서 팝업이 생성됩니다.
    }

    //검수계획등록 submit
    function inspectionPlanRegSubmitClose() {
        // $('#registForm').submit();
        // setTimeout(function () {
        //     alert("검수계획 등록 되었습니다.");
        //     window.opener.location.reload();
        //     self.close();
        // }, 10);
        var params = $("#registForm").serialize();
        $.ajax({
            type: "POST",
            url : '/inspection/register',
            data : params,
            success : function(){
                alert("검수계획 등록 되었습니다.");
                window.opener.location.reload();
                self.close();
                //window.close();
            }
        });
    }

    //검수완료클릭 submit
    function inspectionCompleteSubmitClose() {
        // $('#registForm').submit();
        // setTimeout(function () {
        //     alert("검수완료 되었습니다.");
        //     window.opener.location.reload();
        //     self.close();
        // }, 10);
        var params = $("#registForm").serialize();
        $.ajax({
            type: "POST",
            url : '/inspection/modify',
            data : params,
            success : function(){
                alert("검수완료 되었습니다.");
                window.opener.location.reload();
                self.close();
                //window.close();
            }
        });
    }

    //검수 반품완료클릭 submit
    function inspectionReturnRegSubmitClose() {
        // $('#registForm').submit();
        // setTimeout(function () {
        //     alert("반품완료 되었습니다.");
        //     window.opener.location.reload();
        //     self.close();
        // }, 10);
        var params = $("#registForm").serialize();
        $.ajax({
            type: "POST",
            url : '/inspection/return',
            data : params,
            success : function(){
                alert("반품완료 되었습니다.");
                window.opener.location.reload();
                self.close();
               //window.close();
            }
        });
    }

    //발주 마감완료 클릭 submit
    function inspectionCompleteRegSubmitClose() {
        // $('#registForm').submit();
        // setTimeout(function () {
        //     alert("발주 마감완료 되었습니다.");
        //     window.opener.location.reload();
        //     self.close();
        // }, 10);
        var params = $("#registForm").serialize();
        $.ajax({
            type: "POST",
            url : '/inspection/complete',
            data : params,
            success : function(){
                alert("발주 마감완료 되었습니다.");
                window.opener.location.reload();
                self.close();
                //window.close();
            }
        });

    }

    //검수진척도 셀렉트값 선택
    function changeselect1(value){
        document.getElementById('inspection_degree1').value = value;
        document.getElementById('inspection_etc1').value = value + "% 납품완료";
    }
    function changeselect2(value){
        const target = document.getElementById('inspection_complete_btn');
        document.getElementById('inspection_degree2').value = value;
        document.getElementById('inspection_etc2').value = value + "% 납품완료";
        var x = Number(document.getElementById('inspection_degree1').value);
        var y = Number(document.getElementById('inspection_degree2').value);

        var result =  (x + y)/2;
        if ( result >= 100 ) { result = 100; }
        if ( result <= 0 ) { result = 0; }
        if (result >= 85){
            document.getElementById('inspection_etc3').value = result + "% 납품완료";
            target.disabled = false;
            //alert("검수 진행결과 납품진척도가 85%이상입니다. 검수완료처리 하시기 바랍니다.");
        } else if (result < 85){
            document.getElementById('inspection_etc3').value = result + "% 납품완료";
            target.disabled = true;
            alert("검수 진행결과 납품진척도가 85%이하입니다. 검수취소후 반품처리 하시기 바랍니다.");
        }

    }

    //팝업창 닫기버튼 클릭시 리로드
    function reloadclose(){
        window.opener.location.reload();
        self.close();
    }
