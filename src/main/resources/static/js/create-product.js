try {
  document && document.addEventListener('DOMContentLoaded', onLoad);
} catch (e) {
  console.warn(e);
}

function onLoad() {
  configureAddProduct();
}

function configureAddProduct() {
  const saveBtn = document.querySelector('.save-wrapper button.save');

  saveBtn && configureInputs(saveBtn);
}

function configureInputs(saveBtn) {
  const form = document.querySelector('.add-product-form');
  //const inputId = form.querySelector('#add-product-id');
  const inputDescription = form.querySelector('#add-product-description');
  const inputName = form.querySelector('#add-product-name');
  //const selectType = form.querySelector('#add-product-type');

  const dataInputs = [inputName, inputDescription];

  const checkDisabled = () => {
    if (dataInputs.filter(inp => inp.value.trim()).length === dataInputs.length) {
      saveBtn.disabled = false;
    } else saveBtn.disabled = true;
  }

  dataInputs.forEach(element => {
    if (element && element.tagName === 'INPUT') {
      element.addEventListener('input', checkDisabled);
    } else if (element && element.tagName === 'SELECT') {
      element.addEventListener('change', checkDisabled);
    }
  });

  checkDisabled();

  saveBtn.addEventListener('click', e => {
    const data = {
      checkMark: false,
     // id: inputId.value.trim(),
      name: inputName.value.trim(),
      amount: inputDescription.value.trim()
    };

    addProductToTable(data);

    dataInputs.forEach(element => {
      if (element && element.tagName === 'INPUT') {
        //element.value = '';
      } else if (element && element.tagName === 'SELECT') {
        const options = element.querySelectorAll('option');
        if (options.length) {
          element.value = options[0].value;
        } else {
          element.value = '';
        }
      }
    })
  });
}

function addProductToTable(data) {
  // if (!data) return;
  // const table = document.querySelector('#create-product-table');
  // const tbody = table.querySelector('tbody');
  //
  // const tr = document.createElement('tr');
  //
  // for (const dataKey in data) {
  //   const td = document.createElement('td');
  //   if (dataKey === 'checkMark') {
  //     td.className = data[dataKey] ? 'check-mark-active' : '';
  //     td.innerText = '';
  //   } else {
  //     td.innerText = data[dataKey];
  //   }
  //   tr.append(td);
  // }
  // tbody.append(tr);
}



