/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateEmail(email){
    var regex = /^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/i;
    if(!regex.test(email)){
        document.getElementById('email').value = ''; 
        var error = document.getElementById('error');
        error.className = 'error-context';
        while(error.childNodes[0]){
            error.removeChild(error.childNodes[0]);
        }
        var p = document.createElement('p');
        p.innerHTML = '• Неверный email!';
        error.appendChild(p);
        error.appendChild(document.createElement('br'));
    }
}
function validatePassword(){  
    var pas = document.getElementById('password');
    if(pas.value.length < 6){
        var error = document.getElementById('error');
        while(error.childNodes[0]){
            error.removeChild(error.childNodes[0]);
        }
        error.className = 'error-context';
        var p = document.createElement('p');
        p.innerHTML = '• Длина пароля не менее 6 символов';
        error.appendChild(p);
    }
}
            
function getXmlHttp(){
    var xmlhttp;
    try {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (E) {
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest!=='undefined') {
        xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
}

var xmlhttp;

function go(){
    xmlhttp = getXmlHttp();
    var myText = document.getElementById('test').value;
    var lastId = document.getElementById('lastMesId').value;
    myText = myText.replace(/\r\n?|\n/g,"<br>");
    var url = 'some.do?command=add&test='+myText+'&lastid='+lastId;
    xmlhttp.open('POST', url, true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4) {
            if(xmlhttp.status === 200) {
                document.getElementById('mes').appendChild(add(xmlhttp.responseText));
                afterGo();
            }
        }
    };
    xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlhttp.send(null);
}

function add(val){
    var newDiv = document.createElement('pre');
    newDiv.className = 'my';
    newDiv.innerHTML = val;
    return newDiv;
}

function doDelete(){
    var xmlhttp = getXmlHttp();
    //var myText = document.getElementById('test').value;
    var lastId = document.getElementById('lastMesId').value;
    var url = 'some.do?command=delete&lastid='+lastId;
    xmlhttp.open('POST', url, true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4) {
            if(xmlhttp.status === 200) {
                //document.getElementById('mes').appendChild(add(xmlhttp.responseText));
                afterDelete();
            }
        }
    };
    xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlhttp.send(null);
}

function afterDelete(){
    var formDiv = document.getElementById('form');
    while(formDiv.childNodes[0]){
        formDiv.removeChild(formDiv.childNodes[0]);
    }
    var butt = document.createElement('a');
    butt.className = 'index_button';
    butt.setAttribute('href','/main/some.do?command=index');
    butt.innerHTML = 'Запустить новую записку';
    formDiv.appendChild(document.createElement('br'));
    var del = document.createElement('div');
    del.className = 'del';
    del.innerHTML = 'Диалог удален успешно';
    formDiv.appendChild(butt);
}

function goNew(){
    var xmlHttp = getXmlHttp();
    var myText = document.getElementById('test').value;
    var idNote = document.getElementById('noteId').value;
    myText = myText.replace(/\r\n?|\n/g,"<br>");
    var url = 'some.do?command=add_new&test='+myText+'&note='+idNote;
    xmlHttp.open('POST', url, true);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4) {
            if(xmlHttp.status === 200) {
                var newDiv = document.createElement('pre');
                newDiv.className = 'my';
                newDiv.innerHTML = xmlHttp.responseText;
                document.getElementById('mes').appendChild(newDiv);
                afterGo();
            }
        }
    };
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send(null);
}

function afterGo(){
    var formDiv = document.getElementById('form');
    while(formDiv.childNodes[0]){
        formDiv.removeChild(formDiv.childNodes[0]);
    }
    var butt = document.createElement('a');
    butt.className = 'index_button';
    butt.setAttribute('href','/main/some.do?command=index');
    butt.innerHTML = 'Запустить новую записку';
    formDiv.appendChild(butt);
}

function checkNewMessage(){
    var xmlHttp = getXmlHttp();
    var url = 'some.do?command=check';
    xmlHttp.open('POST', url, true);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4) {
            if(xmlHttp.status === 200) {
                var text = xmlHttp.responseText;
                var hid = document.getElementById('hid');
                if(text === '0' || text === ''){
                    hid.className = 'empty_hid';
                } else {
                    while(hid.childNodes[0]){
                        hid.removeChild(hid.childNodes[0]);
                    }
                    hid.className = 'full_hid';
                    var b = document.createElement('a');
                    b.className = 'new_mes';
                    b.setAttribute('href','/main/some.do?command=new_mes');
                    b.innerHTML = text;
                    hid.appendChild(b);
                }
            }
        }
    };
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send(null);
}
