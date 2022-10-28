        var subcategory = {
            DESKTOP: ["DESKTOP_CPU","DESKTOP_MEMORY","DESKTOP_DISK","DESKTOP_MAINBOARD","DESKTOP_POWER","DESKTOP_GRAPHICSCARD","DESKTOP_CASE"],
            NOTEBOOK: ["MOBILE_CPU","MOBILE_MEMORY","MOBILE_DISK","MOBILE_MAINBOARD","MOBILE_POWER","MOBILE_GRAPHICSCARD","MOBILE_CASE"],
            SERVER: ["SERVER_CPU","SERVER_MEMORY","SERVER_DISK","SERVER_MAINBOARD","SERVER_POWER","SERVER_GRAPHICSCARD","SERVER_CASE"],
            ETC: ["MONITOR","KEYBOARD","MOUSE","COOLER","FAN","ODD"],
            ACCESSORY: ["CABLETIE","GREASE","BOLTS&NUTS","CABLE"],
            TOOLS: ["DRIVER","GLOVE","NIPPER&PLIERS","IRON","CUTTER"]
        }

        function makeSubmenu(value) {
            if (value.length == 0) document.getElementById("categorySelect").innerHTML = "<option></option>";
            else {
                var citiesOptions = "<option value='' disabled selected>-분류선택--------------------------------</option>";
                for (categoryId in subcategory[value]) {
                    citiesOptions += "<option value='" + subcategory[value][categoryId] +"'>" + subcategory[value][categoryId] + "</option>";
                }
                document.getElementById("categorySelect").innerHTML = citiesOptions;
            }
        }

        function resetSelection() {
            document.getElementById("category").selectedIndex = 0;
            document.getElementById("categorySelect").selectedIndex = 0;
        }

        function displaySelected() {
            var searchForm = document.getElementById('searchForm');
            searchForm.method="get";
            //searchForm.action="/product/list";
            var category1 = document.getElementById("category").value;
            var category2 = document.getElementById("categorySelect").value;
            var generic1 = document.getElementById("genericSelect").value;
            var generic2 = document.getElementById("genericKeyword").value;
            alert("아래 선택된 함목으로 검색합니다.\n\n"
                + "분류선택1:"+ category1+"\n"
                + "분류선택2:"+category2+"\n"
                + "항목선택3:"+generic1+" 키워드:"+generic2
            );
            searchForm.submit();
        }
