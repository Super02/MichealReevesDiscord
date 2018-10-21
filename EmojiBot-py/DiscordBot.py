
import discord
from discord.ext import commands
from discord.ext.commands import Bot
import asyncio
import webcolors

bot = commands.Bot(command_prefix='-')

@bot.event
async def on_ready():
	await bot.change_presence(game=discord.Game(name="-help"))
	print ("Bot ready")
	print ("I am running on " + bot.user.name)
	print ("With the ID: " + bot.user.id)

@bot.command(pass_context=True)
async def ping(ctx):
	await bot.say(":ping_pong: pong!")
@bot.command(pass_context=True)
async def info(ctx, user: discord.Member):
	embed = discord.Embed(title="{}'s info".format(user.name), description="This is what I could find...", color=0x00ff00)
	embed.add_field(name="Name", value=user.name, inline=True)
	embed.add_field(name="ID", value=user.id, inline=True)
	embed.add_field(name="Status", value=user.status, inline=True)
	embed.add_field(name="Highest role", value=user.top_role, inline=True)
	embed.add_field(name="Joined", value=user.joined_at, inline=True)
	embed.set_thumbnail(url=user.avatar_url)
	await bot.say(embed=embed)
@bot.command(pass_context=True)
async def serverinfo(ctx):
	embed = discord.Embed(title="{}'s info".format(ctx.message.server.name), description="Here is what I could find...", color=0x00ff00)
	embed.set_author(name="Info")
	embed.add_field(name="Name", value=ctx.message.server.name, inline=True)
	embed.add_field(name="ID", value=ctx.message.server.id, inline=True)
	embed.add_field(name="Emojis", value=len(ctx.message.server.emojis), inline=True)
	embed.add_field(name="Members", value=len(ctx.message.server.members), inline=True)
	embed.set_thumbnail(url=ctx.message.server.icon_url)
	await bot.say(embed=embed)

@bot.command(pass_context=True)
async def kick(ctx, user: discord.Member):
	if(ctx.message.author.id == "305246941992976386" or ctx.message.author.server_permissions.kick_members and user.id != "305246941992976386"):
		try:
			await bot.kick(user)
			embed = discord.Embed(title="<@{}> has been kicked succesfuly".format(user.id), color=0x00ff00)
			embed.add_field(name="Status", value="KICKED", inline=True)
			await bot.say(embed=embed)
		except Exception as e:
			print(type(e))
			embed = discord.Embed(title="Error! {} was not kicked".format(user.name), color=0xff0000)
			embed.add_field(name="Status", value="Error!", inline=True)
			await bot.say(embed=embed)
	else:
			embed = discord.Embed(title="Error!", color=0xff0000)
			embed.add_field(name="Permission", value="denied", inline=True)
			await bot.say(embed=embed)
	
@bot.command(pass_context=True)
async def ban(ctx, user: discord.Member):
	if(ctx.message.author.id == "305246941992976386" or ctx.message.author.server_permissions.ban_members and user.id != "305246941992976386"):
		try:
			await bot.ban(user)
			embed = discord.Embed(title="<@{}> has been banned succesfuly".format(user.id), color=0x00ff00)
			embed.add_field(name="Status", value="BANNED", inline=True)
			await bot.say(embed=embed)
		except Exception as e:
			print(type(e))
			embed = discord.Embed(title="Error! {} was not banned".format(user.name), color=0xff0000)
			embed.add_field(name="Status", value="Error!", inline=True)
			await bot.say(embed=embed)
	else:
		embed = discord.Embed(title="Error!", color=0xff0000)
		embed.add_field(name="Permission", value="denied", inline=True)
		await bot.say(embed=embed)
@bot.command(pass_context=True)
async def unban(ctx, user: discord.Member):
	if(ctx.message.author.id == "305246941992976386" or ctx.message.author.server_permissions.ban_members):
		try:
			await bot.unban(ctx.message.server, user)
			embed = discord.Embed(title="<@{}> has been unbanned succesfuly".format(user.id), color=0x00ff00)
			embed.add_field(name="Status", value="UNBANNED", inline=True)
		except Exception as e:
			print(type(e))
			embed = discord.Embed(title="Error! {} was not unbanned".format(user.name), color=0xff0000)
			embed.add_field(name="Status", value="Error!", inline=True)
			await bot.say(embed=embed)
	else:
		embed = discord.Embed(title="Error!", color=0xff0000)
		embed.add_field(name="Permission", value="denied", inline=True)
		await bot.say(embed=embed)
@bot.command(pass_context=True)
async def say(ctx, *, arg):
	if(ctx.message.author.id == "305246941992976386" or ctx.message.author.server_permissions.administrator):
		await bot.say(arg)
	else:
		await bot.say("Error! You do not have permission for this command.")
@bot.command(pass_context=True)
async def hexcolor(ctx, *, arg):
	if(arg == "nigger" or arg == "nigga"):
		await bot.say("#000000")
	else:
		try:
			await bot.say(webcolors.name_to_hex(arg))
		except ValueError:
			await bot.say(arg + " is not a color that is recognized")
		except Exception as e:
			await bot.say("Unknown error")
			print(e)
@bot.command(pass_context=True, aliases=['purge', 'prune'])
async def clear(ctx, amount=None):
	try:
		if not amount is None:
			amount = int(amount)
	except ValueError:
		msg = await bot.say("Please put in a valid number!")
		msgs=[]
		async for msg in bot.logs_from(ctx.message.channel,limit=2):
			msgs.append(msg)
		await asyncio.sleep(3)
		await bot.delete_messages(msgs)
		return
	
	if(ctx.message.author.id == "305246941992976386" or ctx.message.author.server_permissions.manage_messages):
		msgs=[]
		if amount is None or amount == 0 or amount >= 100:
			async for msg in bot.logs_from(ctx.message.channel,limit=100):
				msgs.append(msg)
		else:
			async for msg in bot.logs_from(ctx.message.channel, limit=amount+1):
				msgs.append(msg)
		await bot.delete_messages(msgs)
		msg = await bot.say("Deleted {} messages.".format(len(msgs)-1))
		await asyncio.sleep(5)
		await bot.delete_message(msg)
@bot.command(pass_context=True)
async def setgame(ctx, *, arg):
	if(ctx.message.author.id == "305246941992976386"):
		try:
			await bot.change_presence(game=discord.Game(name=arg))
			await bot.say("Succesfuly changed bots playing status to: \"{}\"".format(arg))
		except Exception as e:
			bot.say("Unknown error")
			print(type(e))

bot.run("Sorry to say. You're not getting my Discord bot token xD")
