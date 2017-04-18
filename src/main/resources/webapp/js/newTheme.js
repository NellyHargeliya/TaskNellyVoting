function newTheme() {
    var theme = $("#nameTheme").val();
    var option1 = $("#option1").val();
    var option2 = $("#option2").val();
    var crTheme = {"nameTheme": theme, "options":
        [{"optionName": option1, "quantity": 0}, {"optionName": option2, "quantity": 0}]};
    $.ajax({
        type : "POST",
        contentType : "application/json",
        dataType: "json",
        data: JSON.stringify(crTheme),
        url: "themes"
    });
}
