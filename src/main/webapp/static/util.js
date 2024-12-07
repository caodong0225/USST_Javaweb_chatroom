function handleFormSubmit() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    if(username.length % 2 === 1)
    {
        username = username + " "
    }
    if(password.length % 2 === 1)
    {
        password = password + " "
    }

    // 使用加密函数加密数据
    const encryptedUsername = encrypt(username);
    const encryptedPassword = encrypt(password);

    if (!encryptedUsername || !encryptedPassword) {
        alert("加密失败，请检查输入内容！");
        return false; // 阻止表单提交
    }

    // 将加密后的数据放入隐藏字段
    document.getElementById("encryptedUsername").value = encryptedUsername;
    document.getElementById("encryptedPassword").value = encryptedPassword;

    return true; // 允许表单提交
}

function encrypt(a) {
    if (typeof a !== "string") {
        return;
    }
    const key = 0x114;
    const iv = 0x514;
    while (a.length % 2) a += '\0';
    let pos = 0;
    let byteArr = '';
    while (pos < a.length) {
        let x1 = a.charCodeAt(pos);
        let x2 = a.charCodeAt(pos + 1);
        let raw = (0x100 * x1 + x2 + iv) ^ key;
        let raw_hex = raw.toString(16);
        if (raw_hex.length % 2) raw_hex = '0' + raw_hex;
        let group = '';
        for (let i = 0; i < raw_hex.length / 2; i++) {
            let hex = '0x' + raw_hex[2 * i] + raw_hex[2 * i + 1];
            let d = Number.parseInt(hex, 16);
            group += String.fromCharCode(d);
        }
        while (group.length < 2) group = '\0' + group;
        byteArr += group;
        pos += 2;
    }
    return btoa(byteArr);
}