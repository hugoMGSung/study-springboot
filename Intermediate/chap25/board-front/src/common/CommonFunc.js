

export function formatDate(date) {
	var result = date.replace('T', ' ');

	var index = result.lastIndexOf(':');
	result = result.substr(0, index);
	console.log(index);

	return result;
}