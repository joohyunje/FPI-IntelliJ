// 카테고리 안에 서비스 select js

let service1 = 0;
let service2 = 0;
let service3 = 0;
let service4 = 0;
let service5 = 0;
window.onload = () => {
  document.querySelector('.dropbtn').onclick = () => {
    dropdown();
    console.log('버튼 눌')
  }

//   카테고리 1
  document.querySelector('.service1').onclick = () => {
    console.log('잘 눌림')
    if (service1 == 0) {
      service1 = 1;
      dropdown_service1();
    } else {
      service1 = 0;
      fold_service1();
    }
  }
  dropdown_service1 = () => {
    const f = document.querySelector('.dropdown-service1');
    const v = document.querySelector('.dropdown-content');
    v.classList.add('show');
    f.classList.toggle('show');
  }
  fold_service1 = () => {
    const f = document.querySelector('.dropdown-service1');
    f.classList.remove('show')
  }

//   카테고리 2
  document.querySelector('.service2').onclick = () => {
    console.log('잘 눌림')
    if (service2 == 0) {
      service2 = 1;
      dropdown_service2();
    } else {
      service2 = 0;
      fold_service2();
    }
  }
  dropdown_service2 = () => {
    const f = document.querySelector('.dropdown-service2');
    const v = document.querySelector('.dropdown-content');
    v.classList.add('show');
    f.classList.toggle('show');
  }
  fold_service2 = () => {
    const f = document.querySelector('.dropdown-service2');
    f.classList.remove('show')
  }

//   카테고리 3
  document.querySelector('.service3').onclick = () => {
    console.log('잘 눌림')
    if (service3 == 0) {
      service3 = 1;
      dropdown_service3();
    } else {
      service3 = 0;
      fold_service3();
    }
  }
  dropdown_service3 = () => {
    const f = document.querySelector('.dropdown-service3');
    const v = document.querySelector('.dropdown-content');
    v.classList.add('show');
    f.classList.toggle('show');
  }
  fold_service3 = () => {
    const f = document.querySelector('.dropdown-service3');
    f.classList.remove('show')
  }

//   카테고리 4
  document.querySelector('.service4').onclick = () => {
    console.log('잘 눌림')
    if (service4 == 0) {
      service4 = 1;
      dropdown_service4();
    } else {
      service4 = 0;
      fold_service4();
    }
  }
  dropdown_service4 = () => {
    const f = document.querySelector('.dropdown-service4');
    const v = document.querySelector('.dropdown-content');
    v.classList.add('show');
    f.classList.toggle('show');
  }
  fold_service4 = () => {
    const f = document.querySelector('.dropdown-service4');
    f.classList.remove('show')
  }

//   카테고리 5
  document.querySelector('.service5').onclick = () => {
    console.log('잘 눌림')
    if (service5 == 0) {
      service5 = 1;
      dropdown_service5();
    } else {
      service5 = 0;
      fold_service5();
    }
  }
  dropdown_service5 = () => {
    const f = document.querySelector('.dropdown-service5');
    const v = document.querySelector('.dropdown-content');
    v.classList.add('show');
    f.classList.toggle('show');
  }
  fold_service5 = () => {
    const f = document.querySelector('.dropdown-service5');
    f.classList.remove('show')
  }

  dropdown = () => {
    const v = document.querySelector('.dropdown-content');
    const dropbtn = document.querySelector('.dropbtn')
    v.classList.toggle('show');

// dropbtn.style.borderColor = '#8da4d0';
  }


  showService = (value, serviceId) => {
    const dropbtn_content = document.querySelector('.dropbtn_content');
    const dropbtn = document.querySelector('.dropbtn');

    dropbtn_content.innerText = value;
    dropbtn_content.style.color = '#252525';

    const service_input = document.getElementById('service_input');
    service_input.value = serviceId;


  }

}
window.onclick = (e) => {
  if (e.target.matches('.services')) {
    const dropdowns = document.getElementsByClassName("dropdown-content");

    const dropbtn_content = document.querySelector('.dropbtn_content');
    const dropbtn_click = document.querySelector('.dropbtn_click');
    const dropbtn = document.querySelector('.dropbtn');

    let i;
    for (i = 0; i < dropdowns.length; i++) {
      const openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}