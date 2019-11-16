local key = KEYS[1]

local num = tonumber(redis.call('get',key))
if num > 0 then
	num = num -1
	redis.call('set',key,num)
	return num;
else 
	return 0
end
