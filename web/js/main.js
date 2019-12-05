function aplicaMascaraCpf(id) {
    var input = document.getElementById(id);
    switch (input.value.length) {
        case 3:
            input.value += '.';
            break;
        case 7:
            input.value += '.';
            break;
        case 11:
            input.value += '-';
            break;
    }
}

function aplicaMascaraData(id) {
    var input = document.getElementById(id);
    switch (input.value.length) {
        case 2:
            input.value += '/';
            break;
        case 5:
            input.value += '/';
            break;
    }
}