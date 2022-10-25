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
    alert("프로덕트코드 생성 완료 하였습니다. : "+ pcode);
    document.getElementById("pcode").disabled = true;
}

function displayRandomCNum() {
    //계약랜덤코드생성
    //var rnum = Math.random();
    var rnum = randomString();
    var cdate = getYmd10();
    var ccode = "C" + cdate + rnum;
    document.getElementById("ccode").value = ccode;
    alert("계약코드 생성 완료 하였습니다. : "+ ccode);
    document.getElementById("ccode").disabled = true;
}

function displayRandomSNum() {
    //자재랜덤코드생성
    //var rnum = Math.random();
    var rnum = randomString();
    var cdate = getYmd10();
    var scode = "S" + cdate + rnum;
    document.getElementById("scode").value = scode;
    alert("자재코드 생성 완료 하였습니다. : "+ scode);
    document.getElementById("scode").disabled = true;
}

function displayRandomONum() {
    //발주랜덤코드생성
    //var rnum = Math.random();
    var rnum = randomString();
    var cdate = getYmd10();
    var ocode = "O" + cdate + rnum;
    document.getElementById("ocode").value = ocode;
    alert("발주코드 생성 완료 하였습니다. : "+ ocode);
    document.getElementById("ocode").disabled = true;
}