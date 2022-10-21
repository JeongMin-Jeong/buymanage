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
            //랜덤코드생성
            //var rnum = Math.random();
            var rnum = randomString();
            var cdate = getYmd10();
            var pcode = "P" + cdate + rnum;
            document.getElementById("pcode").value = pcode;
            alert("프로덕트코드 생성 완료 하였습니다. : "+ pcode);
        }