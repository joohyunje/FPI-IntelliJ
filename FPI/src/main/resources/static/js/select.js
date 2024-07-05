let service1 = 0;
let service2 = 0;
let service3 = 0;
let service4 = 0;
let service5 = 0;
window.onload=()=>{
document.querySelector('.dropbtn').onclick = ()=>{    
dropdown();
}

//   카테고리 1
document.querySelector('.service1').onclick = ()=>{
console.log('잘 눌림')
if(service1 == 0){
    service1 = 1 ;
    dropdown_service1();
}
else{
    service1 = 0;
    fold_service1();
}
}
dropdown_service1 = () => {
var f = document.querySelector('.dropdown-service1');
var v = document.querySelector('.dropdown-content');
v.classList.add('show');
f.classList.toggle('show');
}
fold_service1 = () => {
var f = document.querySelector('.dropdown-service1');
f.classList.remove('show')
}

//   카테고리 2
document.querySelector('.service2').onclick = ()=>{
console.log('잘 눌림')
if(service2 == 0){
    service2 = 1 ;
    dropdown_service2();
}
else{
    service2 = 0;
    fold_service2();
}
}
dropdown_service2 = () => {
var f = document.querySelector('.dropdown-service2');
var v = document.querySelector('.dropdown-content');
v.classList.add('show');
f.classList.toggle('show');
}
fold_service2 = () => {
var f = document.querySelector('.dropdown-service2');
f.classList.remove('show')
}

//   카테고리 3
document.querySelector('.service3').onclick = ()=>{
console.log('잘 눌림')
if(service3 == 0){
    service3 = 1 ;
    dropdown_service3();
}
else{
    service3 = 0;
    fold_service3();
}
}
dropdown_service3 = () => {
var f = document.querySelector('.dropdown-service3');
var v = document.querySelector('.dropdown-content');
v.classList.add('show');
f.classList.toggle('show');
}
fold_service3 = () => {
var f = document.querySelector('.dropdown-service3');
f.classList.remove('show')
}

//   카테고리 4
document.querySelector('.service4').onclick = ()=>{
console.log('잘 눌림')
if(service4 == 0){
    service4= 1 ;
    dropdown_service4();
}
else{
    service4 = 0;
    fold_service4();
}
}
dropdown_service4 = () => {
var f = document.querySelector('.dropdown-service4');
var v = document.querySelector('.dropdown-content');
v.classList.add('show');
f.classList.toggle('show');
}
fold_service4 = () => {
var f = document.querySelector('.dropdown-service4');
f.classList.remove('show')
}

//   카테고리 5
document.querySelector('.service5').onclick = ()=>{
console.log('잘 눌림')
if(service5 == 0){
    service5 = 1 ;
    dropdown_service5();
}
else{
    service5 = 0;
    fold_service5();
}
}
dropdown_service5 = () => {
var f = document.querySelector('.dropdown-service5');
var v = document.querySelector('.dropdown-content');
v.classList.add('show');
f.classList.toggle('show');
}
fold_service5 = () => {
var f = document.querySelector('.dropdown-service5');
f.classList.remove('show')
}

dropdown = () => {
var v = document.querySelector('.dropdown-content');
var dropbtn = document.querySelector('.dropbtn')
v.classList.toggle('show');

// dropbtn.style.borderColor = '#8da4d0';
}



showService=(value)=>{
var dropbtn_content = document.querySelector('.dropbtn_content');
var dropbtn = document.querySelector('.dropbtn');

dropbtn_content.innerText = value;
dropbtn_content.style.color = '#252525';

}
}
window.onclick= (e)=>{
if(e.target.matches('.services')){
var dropdowns = document.getElementsByClassName("dropdown-content");

var dropbtn_content = document.querySelector('.dropbtn_content');
var dropbtn_click = document.querySelector('.dropbtn_click');
var dropbtn = document.querySelector('.dropbtn');

var i;
for (i = 0; i < dropdowns.length; i++) {
  var openDropdown = dropdowns[i];
  if (openDropdown.classList.contains('show')) {
    openDropdown.classList.remove('show');
  }
}
}
}