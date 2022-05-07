onLoad();

function onLoad() {
  listenAskPrices();
  createOrder();
}

function listenAskPrices() {
  const btn = document.querySelector('.ordering button');
  btn && btn.addEventListener('click', e => {

  });
}

function createOrder() {
  const createBtn = document.querySelector('.create-order button');
  const inputsWrapper = document.querySelector('.processing-inputs');

  const inputs = inputsWrapper.querySelectorAll('input');
  const selects = inputsWrapper.querySelectorAll('select');

  const dataInputs = [...inputs, ...selects];

  const checkForDisabled = () => {
    if (dataInputs.filter(el => el.value.trim()).length === dataInputs.length) createBtn.disabled = false;
    else createBtn.disabled = true;
  };

  const clearValues = () => {
    dataInputs.forEach(el => {
      if (el && el.tagName === 'INPUT') el.value = '';
      else if (el && el.tagName === 'SELECT') {
        const options = el.querySelectorAll('option');
        if (options.length) {
          el.value = options[0].value;
        } else {
          el.value = '';
        }
      }
    })
  };

  dataInputs.forEach(el => {
    if (el && el.tagName === 'INPUT') el.addEventListener('input', checkForDisabled);
    else if (el && el.tagName === 'SELECT') el.addEventListener('select', checkForDisabled);
  })

  createBtn.addEventListener('click', e => {
    const data = dataInputs.reduce((acc, el) => {
      if (el && el.dataset && el.dataset.key) {
        acc[el.dataset.key] = el.value.trim()
      } else {
        acc[el.id] = el.value.trim();
      }
      return acc;
    }, {});

    console.log(data);

    clearValues();
  });
}