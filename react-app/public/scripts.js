
var mainTbl = document.getElementById("myTable"); //получаем нашу таблицу 
var tds = mainTbl.getElementsByTagName("td"); //все ячейки из таблицы

//начальная инициализация таблицы
function defaultTableInit() {
    clearTable(); 
    createDefaultTable();
    hideAndShowElements();
    sortTable(); 
}

function groupTableInit() {
    clearTable(); 
    createGroupTable();
}

defaultTableInit(); 

//отслеживание состояния селектора
document.querySelector("select").addEventListener('change', function (e) {
    if (document.getElementById("group-type-select").options.selectedIndex == 1) {
        document.getElementById("show_link").style.display = 'none';
        groupTableInit();  
    }
    else {
        defaultTableInit(); 
    }  
    }); 

function createDefaultTable() {

    fillTitleRow();
    fillDataRows(); 
     
    function fillTitleRow() {

        var row = document.createElement("TR");
    
        for (let i = 0; i < 5; i++) {
            row.appendChild(document.createElement("TD"));  
        }
    
        mainTbl.appendChild(row);  
    
        for (let i = 0; i < 5; i++) {
            tds[i].style.cursor = 'pointer';
        } 
       
        tds[0].innerHTML = "Дата";  
        tds[1].innerHTML = "Время";
        tds[2].innerHTML = "Тип";  
        tds[3].innerHTML = "Приход"; 
        tds[4].innerHTML = "Расход";  
     
    } 

    function fillDataRows() {
        var mainTbl = document.getElementById("myTable") //получаем нашу таблицу 
    
        for (let index = 0; index < myStatementData.length; index++) {
            var row = document.createElement("TR");
            var td1 = document.createElement("TD");
            var td2 = document.createElement("TD");
            var td3 = document.createElement("TD");
            var td4 = document.createElement("TD");
            var td5 = document.createElement("TD");
    
            var date = new Date(myStatementData[index].date);
    
            row.id = myStatementData[index]._id;  
            
            td1.innerHTML = formatDate(date); 
            td1.setAttribute('type','date');  
    
            td2.innerHTML = formatTime(date);
            td2.setAttribute('type','time');  
    
            td3.innerHTML = myStatementData[index].type;     
            td3.setAttribute('type','type'); 
    
            if (myStatementData[index].amount > 0) {
                td4.innerHTML = myStatementData[index].amount + " ₽"; 
                td4.style.color = "#37761C";  
            } 
            else {
                td5.innerHTML = myStatementData[index].amount+ " ₽" ; 
                td5.style.color = "#FF0000"; 
            }  
    
            td4.setAttribute('type','profit'); 
            td5.setAttribute('type','spending'); 
      
            row.appendChild(td1);  
            row.appendChild(td2);  
            row.appendChild(td3); 
            row.appendChild(td4);
            row.appendChild(td5);  
            mainTbl.appendChild(row);  
        }
    }   

    function formatDate(date) {
 
        var dd = date.getDate(); 
        if (dd < 10) dd = '0' + dd;
      
        var mm = date.getMonth() + 1;
        if (mm < 10) mm = '0' + mm; 
       
        var yy = date.getFullYear() % 100;
        if (yy < 10) yy = '0' + yy;
      
        return dd + '.' + mm + '.' + yy;
    } 
    
    function formatTime(date) { 
    
        var hh = date.getHours();
        if (hh < 10) hh = '0' + hh;
      
        var mm = date.getMinutes();
        if (mm < 10) mm = '0' + mm;
      
        var ss = date.getSeconds();
        if (ss < 10) ss = '0' + ss;
      
        return hh + ':' + mm + ':' + ss; 
    } 
}

function createGroupTable() {

    fillTitleRow();
    fillDataRows();
  
    function fillTitleRow() {

        var row = document.createElement("TR");
    
        for (let i = 0; i < 2; i++) {
            row.appendChild(document.createElement("TD"));  
        }
    
        mainTbl.appendChild(row);  
    
        for (let i = 0; i < 2; i++) {
            tds[i].style.cursor = 'pointer';
        }  
       
        tds[0].innerHTML = "Дата";    
        tds[1].innerHTML = "Сумма операций";   
    } 
  
  
    function fillDataRows() {

        var groupedStatementData = myStatementData.reduce((acc, n) => {
            let obj = acc.find(
                m => new Date(m.date).toLocaleDateString("ru-RU") === new Date(n.date).toLocaleDateString("ru-RU")
                );
            if (!obj) {  
              acc.push(obj = { _id: n._id, date: n.date, type: n.type, amount: 0 }); 
            }  
            obj.amount += n.amount;    
            return acc; 
          }, []);  
 
        for (let index = 0; index < groupedStatementData.length; index++) {

            var row = document.createElement("TR");
            var td1 = document.createElement("TD");
            var td4 = document.createElement("TD");
    
            var date = new Date(groupedStatementData[index].date);
    
            row.id = groupedStatementData[index]._id;   
            
            td1.innerHTML = formatDate(date); 
            td1.setAttribute('type','date');  
    

            td4.innerHTML = groupedStatementData[index].amount + " ₽"; 
            td4.setAttribute('type','profit'); 
    
            if (groupedStatementData[index].amount > 0) {
                td4.innerHTML = groupedStatementData[index].amount + " ₽"; 
                td4.style.color = "#37761C";  
            } 
            else { 
                td4.style.color = "#FF0000";  
            }  
 
            row.appendChild(td1);  
            row.appendChild(td4);

            mainTbl.appendChild(row);  
        } 
    }
    
    
    function formatDate(date) {
 
        var dd = date.getDate(); 
        if (dd < 10) dd = '0' + dd;
      
        var mm = date.getMonth() + 1;
        if (mm < 10) mm = '0' + mm; 
       
        var yy = date.getFullYear() % 100;
        if (yy < 10) yy = '0' + yy;
      
        return dd + '.' + mm + '.' + yy;
    } 
    
    function formatTime(date) { 
    
        var hh = date.getHours();
        if (hh < 10) dd = '0' + hh;
      
        var mm = date.getMinutes();
        if (mm < 10) mm = '0' + mm;
      
        var ss = date.getSeconds();
        if (ss < 10) ss = '0' + ss;
      
        return hh + ':' + mm + ':' + ss; 
    } 
} 
  
function clearTable() {
    while (mainTbl.hasChildNodes()) { 
            mainTbl.removeChild(mainTbl.lastChild);
    } 
}  
  
function hideAndShowElements() {

    var countOfClosedColumns = 0;    
    var params = ['date','time','type','profit','spending'];
    var showLink = document.getElementById("show_link");

    for (let i = 0; i < 5; i++) {

        tds[i].onclick = () => {
            countOfClosedColumns++; 
            showLink.style.display = 'inline';
            if (countOfClosedColumns < 5) {
                tds[i].style.display = 'none';  
            } 
            for (let j = 0; j < tds.length; j++) {
                if (tds[j].getAttribute('type') == params[i] && countOfClosedColumns < 5) {
                    tds[j].style.display = 'none';   
                }     
            } 
        }  
    }  
      
    showLink.onclick = () => {
        countOfClosedColumns = 0;
        var mainTbl = document.getElementById("myTable") //получаем нашу таблицу 
        var tds = mainTbl.getElementsByTagName("td"); //все ячейки из таблицы
    
        for (let i = 0; i < tds.length; i++) {
            tds[i].style.display = 'table-cell'; 
        }
    
        showLink.style.display = 'none';
    }
}  
     
function sortTable() {   

    function getDateFromStatement(id) { 
        for (let index = 0; index < myStatementData.length; index++) {
            if (id == myStatementData[index]._id) {
                return new Date(myStatementData[index].date);
            }
        }
    }   
    var trs = mainTbl.getElementsByTagName("tr");
    var sortedRows = Array.from(trs) 
        .slice(1)  
        .sort((rowA, rowB) => (getDateFromStatement(rowA.id) > getDateFromStatement(rowB.id) ? 1 : -1));
    mainTbl.append(...sortedRows); 
}  

   
    

    
                        