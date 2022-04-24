try {
  document && document.addEventListener('DOMContentLoaded', onLoad);
} catch (e) {
  console.warn(e);
}

const onAddRow = () => {};

function onLoad() {
  const buttonAddResource = document.querySelector('[data-related=add-resource]');
  const modal = document.querySelector('[data-modal]');

  if (modal) {
    modal && configureModal(modal);
    modal && configureModalForm(modal);
  }

  buttonAddResource && buttonAddResource.addEventListener('click', e => {
    if (modal && modal.classList.contains('hide')) toggleModal(modal, true);
  });
}

function toggleModal(modal, toggler) {
  if (toggler) modal.classList.remove('hide');
  else modal.classList.add('hide');
}

function configureModal(modal) {
  const closeBtn = modal.querySelector('.modal-close');
  const modalWrapper = modal.querySelector('.modal-wrapper');
  closeBtn && closeBtn.addEventListener('click', e => {
    toggleModal(modal, false);
  });
  modal && modal.addEventListener('click', e => {
    e.stopPropagation();
    if (e.target.classList.contains('modal-wrapper')) toggleModal(modal, false);
  });
}

function configureModalForm(modal) {
  const inputId = modal.querySelector('#in-id');
  const inputName = modal.querySelector('#in-name');
  const inputDescription = modal.querySelector('#in-description');
  const form = modal.querySelector('form.modal-form');
  //TODO вернуть валидацию как и при авторизации
  //
  // form && form.addEventListener('submit', e => {
  //   e.preventDefault();
  //   if (!inputId || !inputName || !inputDescription) return;
  //
  //   if (
  //     !inputId.value.trim() ||
  //     !inputName.value.trim() ||
  //     !inputDescription.value.trim()
  //   ) return alert('Заполните поля');
  //
  //   const data = {
  //     checkMark: false,
  //     id: inputId.value.trim(),
  //     name: inputName.value.trim(),
  //     description: inputDescription.value.trim()
  //   };
  //
  //   addResourceToTable(data);
  //   onAddRow();
  //   toggleModal(modal, false);
  //
  //   inputId.value = '';
  //   inputName.value = '';
  //   inputDescription.value = '';
  // });
}

function addResourceToTable(data) {
  if (!data) return;
  const table = document.querySelector('#resources_table');
  const tbody = table.querySelector('tbody');

  const tr = document.createElement('tr');

  for (const dataKey in data) {
    const td = document.createElement('td');
    if (dataKey === 'checkMark') {
      td.className = data[dataKey] ? 'check-mark-active' : '';
      td.innerText = '';
    } else {
      td.innerText = data[dataKey];
    }
    tr.append(td);
  }
  tbody.append(tr);
}