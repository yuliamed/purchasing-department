try {
    document && document.addEventListener('DOMContentLoaded', onLoad);

} catch (e) {
    console.warn(e);
}

const onAddRow = () => {
};

function onLoad() {

    const getSort = ({ target }) => {
        const order = (target.dataset.order = -(target.dataset.order || -1));
        const index = [...target.parentNode.cells].indexOf(target);
        const collator = new Intl.Collator(['en', 'ru'], { numeric: true });
        const comparator = (index, order) => (a, b) => order * collator.compare(
            b.children[index].innerHTML,
            a.children[index].innerHTML
        );

        for(const tBody of target.closest('table').tBodies)
            tBody.append(...[...tBody.rows].sort(comparator(index, order)));

        for(const cell of target.parentNode.cells)
            cell.classList.toggle('sorted', cell === target);
    };
    document.querySelectorAll('.content-table thead').forEach(tableTH => tableTH.addEventListener('click', () => getSort(event)));

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
    //const inputId = modal.querySelector('#in-id');
    const inputName = modal.querySelector('#in-name');
    const inputDescription = modal.querySelector('#in-description');
    const form = modal.querySelector('form.modal-form');

    form && form.addEventListener('submit', e => {

        //if (!inputId || !inputName || !inputDescription){
            if (!inputName || !inputDescription){
            e.preventDefault();
            return;}

        if (
            //!inputId.value.trim() ||
            !inputName.value.trim() ||
            !inputDescription.value.trim()
        ) {
            e.preventDefault();
            return alert('Заполните поля');
        }

        // const data = {
        //     checkMark: false,
        //     id: inputId.value.trim(),
        //     name: inputName.value.trim(),
        //     description: inputDescription.value.trim()
        // };

        // addResourceToTable(data);
        // onAddRow();
        // toggleModal(modal, false);
        //
        // inputId.value = '';
        // inputName.value = '';
        // inputDescription.value = '';
    });
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