const convert = require("./index");
test('Test convert number 1', () => { expect(convert(1)).toBe("I"); })
test('Test convert number 4', () => { expect(convert(4)).toBe("IV"); })
test('Test convert number 7', () => { expect(convert(7)).toBe("VII"); })
test('Test convert number 9', () => { expect(convert(9)).toBe("IX"); })
test('Test convert number 13', () => { expect(convert(13)).toBe("XIII"); })
test('Test convert number 38', () => { expect(convert(38)).toBe("XXXVIII"); })
test('Test convert number 10', () => { expect(convert(10)).toBe("X"); })
test('Test convert number 40', () => { expect(convert(40)).toBe("XL"); })
test('Test convert number 49', () => { expect(convert(49)).toBe("XLIX"); })
test('Test convert number 50', () => { expect(convert(50)).toBe("L"); })
test('Test convert number 55', () => { expect(convert(55)).toBe("LV"); })
test('Test convert number 60', () => { expect(convert(60)).toBe("LX"); })